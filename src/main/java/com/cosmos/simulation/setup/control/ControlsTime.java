package com.cosmos.simulation.setup.control;

import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.io.input.InputTime;
import com.cosmos.simulation.io.input.time.InputFromTimeTerm;
import com.cosmos.simulation.model.Time;
import java.util.List;
import java.util.Objects;

public class ControlsTime implements Controls<Time> {
  private final InputTime input;
  private Time time;

  public ControlsTime() {
    this(new InputFromTimeTerm());
  }

  public ControlsTime(InputTime input) {
    this.input = Objects.requireNonNull(input);
  }

  /** Retains the previous settings if input does not produce a new Time. */
  @Override
  public Time control(MenuTerm term) {
    if (term == null) {
      term = new MenuTerm();
    }
    List<String> options = List.of("Set Time", "Show Time", "Back");
    while (true) {
      term.clear();
      int ind = term.choiseOption(options);
      switch (ind) {
        case 0:
          term.clear();
          Time entered = input.inputTime(term);
          if (entered != null) {
            time = entered;
          }
          break;
        case 1:
          term.clear();
          if (time == null) {
            term.showMsg("Time is not set.\n");
          } else {
            term.showMsg("Step Count: " + time.getStepCount() + "\n");
            term.showMsg("Time Step: " + time.getTimeStep() + "\n");
          }
          term.showMsg("Press Enter to continue...");
          term.getInput();
          break;
        case 2:
          term.clear();
          return time;
      }
    }
  }
}
