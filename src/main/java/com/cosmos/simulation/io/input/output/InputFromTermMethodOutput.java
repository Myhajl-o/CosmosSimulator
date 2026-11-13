package com.cosmos.simulation.io.input.output;

import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.io.input.InputMethodOutput;
import com.cosmos.simulation.io.input.InputPath;
import com.cosmos.simulation.io.output.Output;
import com.cosmos.simulation.io.output.OutputToFile;
import com.cosmos.simulation.io.output.OutputToTerm;
import com.cosmos.simulation.physics.Constants;

public class InputFromTermMethodOutput implements InputMethodOutput {

  /** Returns the selected output method, or null when Back is selected. */
  @Override
  public Output inputMeth(MenuTerm term) {

    term.clear();
    int ind = term.choiseOption(Constants.Massanges.inMethodOut);

    return switch (ind) {
      case 0 -> new OutputToTerm();
      case 1 -> new OutputToFile(InputPath.input(term));
      case 2 -> null;
      default -> throw new IllegalStateException("Unknown output method selection");
    };
  }
}
