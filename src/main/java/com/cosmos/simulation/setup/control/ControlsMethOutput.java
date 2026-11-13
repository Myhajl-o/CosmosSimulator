package com.cosmos.simulation.setup.control;

import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.io.output.Output;
import com.cosmos.simulation.physics.Constants;

public class ControlsMethOutput implements Controls<Output> {
  public Output control(MenuTerm term) {
    int ind;
    Output out = null;
    while (true) {
      term.clear();
      ind = term.choiseOption(Constants.Massanges.controlMethodOut);
      switch (ind) {
        case 0:
          out = Constants.AllListsInput.inputMethodOut.get(0).inputMeth(term);
          break;
        case 1:
          return out;
      }
    }
  }
}
