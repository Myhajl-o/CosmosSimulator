package com.cosmos.simulation.setup.control;

import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.model.Time;
import com.cosmos.simulation.physics.Constants;

public class ControlsTime implements Controls<Time> {

  private void showTime(MenuTerm term, Time time) {
    if (time == null) {
      term.showMsg("Time is not set.\n");
    } else {
      term.showMsg("Step Count: " + time.getStepCount() + "\n");
      term.showMsg("Time Step: " + time.getTimeStep() + "\n");
    }
  }

  /** Retains the previous settings if input does not produce a new Time. */
  @Override
  public Time control(MenuTerm term) {
    if (term == null) {
      term = new MenuTerm();
    }
    Time time = null;
    int ind, i;

    while (true) {
      term.clear();
      ind = term.choiseOption(Constants.Massanges.controlTime);
      switch (ind) {
        case 0:
          term.clear();
          i = term.choiseOption(Constants.Massanges.controlCreateTime);
          if (i == 3) {
            break;
          }
          time = Constants.AllListsInput.inputTime.get(i).inputTime(term);
          break;
        case 1:
          term.clear();
          showTime(term, time);
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
