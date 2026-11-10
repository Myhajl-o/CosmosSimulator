package com.cosmos.simulation.io;

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
import org.jline.terminal.Attributes;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.jline.utils.Display;
import org.jline.utils.InfoCmp.Capability;

/** General-purpose terminal menus, messages and line input. Use one shared instance. */
public final class MenuTerm {
  private enum Key { UP, DOWN, ENTER, IGNORE }

  private final Terminal terminal;
  private final LineReader lineReader;
  private final BindingReader keyReader;
  private final KeyMap<Key> keys = new KeyMap<>();

  public MenuTerm() {
    this(openTerminal());
  }

  MenuTerm(Terminal terminal) {
    this.terminal = Objects.requireNonNull(terminal);
    lineReader = LineReaderBuilder.builder().terminal(terminal).build();
    lineReader.option(LineReader.Option.DISABLE_EVENT_EXPANSION, true);
    keyReader = new BindingReader(terminal.reader());
    keys.setNomatch(Key.IGNORE);
    keys.setUnicode(Key.IGNORE);
    bindArrow(Key.UP, Capability.key_up, "\033[A", "\033OA");
    bindArrow(Key.DOWN, Capability.key_down, "\033[B", "\033OB");
    keys.bind(Key.ENTER, "\r", "\n");
  }

  /** Returns the zero-based option index. Arrow keys move; Enter confirms. */
  public int choiseOption(List<String> options) {
    List<String> labels = List.copyOf(options);
    if (labels.isEmpty()) {
      throw new IllegalArgumentException("The option list must not be empty");
    }
    if (labels.stream().anyMatch(label -> label.codePoints().anyMatch(Character::isISOControl))) {
      throw new IllegalArgumentException("Options must be single-line text without control characters");
    }

    Attributes saved = terminal.enterRawMode();
    Display display = new Display(terminal, false);
    int selected = 0;
    try {
      terminal.writer().print("\r");
      terminal.flush();
      while (true) {
        int columns = terminal.getWidth() > 1 ? terminal.getWidth() : 80;
        int height = terminal.getHeight() > 1 ? terminal.getHeight() : 24;
        display.resize(height, columns);
        int first = Math.max(0, selected - (height - 2));
        List<AttributedString> rows = menuRows(labels, selected, first, height - 1, columns - 1);
        display.update(rows, (selected - first) * (columns + 1));
        Key key = keyReader.readBinding(keys);
        if (key == null) {
          throw new EndOfFileException();
        }
        switch (key) {
          case UP -> selected = Math.floorMod(selected - 1, labels.size());
          case DOWN -> selected = (selected + 1) % labels.size();
          case ENTER -> {
            // Leave subsequent messages below the entire menu.
            display.update(rows, (rows.size() - 1) * (columns + 1));
            terminal.writer().print("\r\n");
            terminal.flush();
            return selected;
          }
          default -> { }
        }
      }
    } finally {
      terminal.setAttributes(saved);
    }
  }

  /** Prints the supplied text as-is, without adding a line break. */
  public void showMsg(String msg) {
    terminal.writer().print(Objects.requireNonNull(msg));
    terminal.flush();
  }

  /** Reads a line confirmed with Enter; the returned string excludes the line break. */
  public String getInput() {
    return lineReader.readLine("");
  }

  /** Clears the visible screen and requests scrollback clearing where supported. */
  public void clear() {
    terminal.puts(Capability.clear_screen);
    terminal.writer().print("\033[3J");
    terminal.flush();
  }

  private static Terminal openTerminal() {
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

  private static List<AttributedString> menuRows(
      List<String> labels, int selected, int first, int count, int width) {
    List<AttributedString> rows = new ArrayList<>();
    for (int i = first; i < Math.min(labels.size(), first + count); i++) {
      AttributedString label = new AttributedString(labels.get(i)).columnSubSequence(0, width);
      AttributedStyle style = i == selected
          ? AttributedStyle.DEFAULT.foreground(AttributedStyle.BLACK).background(AttributedStyle.WHITE)
          : AttributedStyle.DEFAULT.foreground(AttributedStyle.WHITE).background(AttributedStyle.BLACK);
      rows.add(new AttributedString(label + " ".repeat(width - label.columnLength()), style));
    }
    return rows;
  }
}
