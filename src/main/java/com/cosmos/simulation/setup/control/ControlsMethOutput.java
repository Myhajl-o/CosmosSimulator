package com.cosmos.simulation.setup.control;

import java.util.List;
import java.util.Arrays;

import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.io.input.InputMethodOutput;
import com.cosmos.simulation.io.input.output.InputFromTermMethodOutput;
import com.cosmos.simulation.io.output.Output;

public class ControlsMethOutput implements Controls<Output> {
  public Output control(MenuTerm term) {
    List<String> option = Arrays.asList(
        "Add Output",
        "Back");
    int ind;
    Output out = null;
    while (true) {
      term.clear();
      ind = term.choiseOption(option);
      switch (ind) {
        case 0:
          InputMethodOutput methOut = new InputFromTermMethodOutput();
          out = methOut.inputMeth(term);
          break;
        case 1:
          return out;
      }
    }

  }
}
