package com.cosmos.simulation.io;

import com.cosmos.simulation.io.input.model.VecTemp;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jline.keymap.BindingReader;
import org.jline.keymap.KeyMap;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Attributes;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.jline.utils.Display;
import org.jline.utils.InfoCmp.Capability;

/** Terminal interaction. Close this instance when the application exits. */
public class InOutTerm implements InOut<String>, AutoCloseable {
  private enum Key { UP, DOWN, ENTER, BACKSPACE, TEXT, CANCEL, EOF, IGNORE }

  private final Terminal terminal;
  private final LineReader input;
  private final BindingReader keyReader;
  private final KeyMap<Key> keys = new KeyMap<>();

  public InOutTerm() {
    this(createTerminal());
  }

  /** The supplied terminal is closed together with this instance. */
  public InOutTerm(Terminal terminal) {
    this.terminal = Objects.requireNonNull(terminal);
    input = LineReaderBuilder.builder().terminal(terminal).build();
    input.option(LineReader.Option.DISABLE_EVENT_EXPANSION, true);
    keyReader = new BindingReader(terminal.reader());
    keys.setUnicode(Key.TEXT);
    keys.setNomatch(Key.IGNORE);
    for (char c = 32; c < 127; c++) {
      keys.bind(Key.TEXT, String.valueOf(c));
    }
    bindArrow(Key.UP, Capability.key_up, "\033[A", "\033OA");
    bindArrow(Key.DOWN, Capability.key_down, "\033[B", "\033OB");
    keys.bind(Key.ENTER, "\r", "\n");
    keys.bind(Key.BACKSPACE, "\b", "\177");
    keys.bind(Key.CANCEL, "\003");
    keys.bind(Key.EOF, "\004");
    keys.bind(Key.IGNORE, "\033[C", "\033[D", "\033OC", "\033OD");
  }

  private static Terminal createTerminal() {
    try {
      return TerminalBuilder.builder().system(true).dumb(false).build();
    } catch (IOException e) {
      throw new UncheckedIOException("Could not open the terminal", e);
    }
  }

  private void bindArrow(Key key, Capability capability, String... sequences) {
    keys.bind(key, sequences);
    String sequence = KeyMap.key(terminal, capability);
    if (sequence != null) {
      keys.bind(key, sequence);
    }
  }

  public void clean() {
    clear();
  }

  /** Clears the screen and scrollback where supported by the terminal. */
  public void clear() {
    terminal.puts(Capability.clear_screen);
    terminal.writer().print("\033[3J");
    terminal.flush();
  }

  /** Returns the zero-based index of the selected option. */
  public int selectOption(List<String> options) {
    List<String> labels = checkedLabels(options);
    if (labels.isEmpty()) {
      throw new IllegalArgumentException("The option list must not be empty");
    }
    Attributes saved = terminal.enterRawMode();
    Display display = new Display(terminal, false);
    int selected = 0;
    try {
      while (true) {
        int width = displayWidth();
        int height = Math.max(1, terminal.getHeight());
        display.resize(height, width + 1);
        int visible = Math.max(1, height - 1);
        int first = Math.max(0, selected - visible + 1);
        List<AttributedString> rows = new ArrayList<>();
        for (int i = first; i < Math.min(labels.size(), first + visible); i++) {
          AttributedStyle style = i == selected
              ? AttributedStyle.DEFAULT.foreground(0).background(7)
              : AttributedStyle.DEFAULT.foreground(7).background(0);
          AttributedString label = new AttributedString(labels.get(i)).columnSubSequence(0, width);
          rows.add(new AttributedString(label + " ".repeat(width - label.columnLength()), style));
        }
        display.update(rows, (selected - first) * (width + 1));
        switch (readKey()) {
          case UP -> selected = Math.floorMod(selected - 1, labels.size());
          case DOWN -> selected = (selected + 1) % labels.size();
          case ENTER -> { return selected; }
          default -> { }
        }
      }
    } finally {
      finishInteraction(saved);
    }
  }

  @Override
  public String input() {
    return input.readLine("");
  }

  @Override
  public void output(String value) {
    terminal.writer().print(Objects.requireNonNull(value));
    terminal.flush();
  }

  public String readText(String str) {
    showMessage(Objects.requireNonNull(str));
    return input();
  }

  /** Up/down changes fields; Enter advances and confirms on the z field. */
  public VecTemp readVector(String mainStr, List<String> otherStr) {
    Objects.requireNonNull(mainStr);
    List<String> labels = checkedLabels(otherStr);
    if (labels.size() != 3) {
      throw new IllegalArgumentException("Exactly three labels are required for x, y and z");
    }
    StringBuilder[] values = {new StringBuilder(), new StringBuilder(), new StringBuilder()};
    Attributes saved = terminal.enterRawMode();
    Display display = new Display(terminal, false);
    int selected = 0;
    try {
      while (true) {
        int width = displayWidth();
        display.resize(Math.max(1, terminal.getHeight()), width + 1);
        List<AttributedString> rows = new ArrayList<>();
        for (String heading : mainStr.split("\\R", -1)) {
          rows.add(new AttributedString(heading).columnSubSequence(0, width));
        }
        int headingRows = rows.size();
        if (terminal.getHeight() > 0 && headingRows + 3 >= terminal.getHeight()) {
          throw new IllegalStateException("Increase the terminal height to display all fields");
        }
        int cursorColumn = 0;
        for (int i = 0; i < 3; i++) {
          AttributedString row = new AttributedString(labels.get(i) + values[i]);
          int offset = Math.max(0, row.columnLength() - width);
          rows.add(row.columnSubSequence(offset, offset + width));
          if (i == selected) {
            cursorColumn = Math.min(width, row.columnLength());
          }
        }
        display.update(rows, (headingRows + selected) * (width + 1) + cursorColumn);
        switch (readKey()) {
          case UP -> selected = Math.floorMod(selected - 1, 3);
          case DOWN -> selected = (selected + 1) % 3;
          case ENTER -> {
            if (selected == 2) {
              return new VecTemp(values[0].toString(), values[1].toString(), values[2].toString());
            }
            selected++;
          }
          case BACKSPACE -> {
            StringBuilder value = values[selected];
            if (!value.isEmpty()) {
              value.delete(value.offsetByCodePoints(value.length(), -1), value.length());
            }
          }
          case TEXT -> values[selected].append(keyReader.getLastBinding());
          default -> { }
        }
      }
    } finally {
      finishInteraction(saved);
    }
  }

  private int displayWidth() {
    return terminal.getWidth() > 1 ? terminal.getWidth() - 1 : 79;
  }

  private static List<String> checkedLabels(List<String> labels) {
    List<String> copy = List.copyOf(labels);
    if (copy.stream().anyMatch(label -> label.codePoints().anyMatch(Character::isISOControl))) {
      throw new IllegalArgumentException("Labels must be single-line strings without control characters");
    }
    return copy;
  }

  /** Reads a complete key sequence. */
  private Key readKey() {
    Key key = keyReader.readBinding(keys);
    if (key == null || key == Key.EOF) {
      throw new EndOfFileException();
    }
    if (key == Key.CANCEL) {
      throw new UserInterruptException("");
    }
    return key;
  }

  private void finishInteraction(Attributes saved) {
    try {
      terminal.writer().print("\033[0m");
      terminal.writer().println();
      terminal.flush();
    } finally {
      terminal.setAttributes(saved);
    }
  }

  public void showMessage(String msg) {
    output(msg);
  }

  @Override
  public void close() {
    try {
      terminal.close();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
