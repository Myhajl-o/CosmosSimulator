package com.cosmos.simulation.io.input;

import com.cosmos.simulation.model.PackegeSetup;
import com.cosmos.simulation.setup.ChoiceOptionManager;
import com.cosmos.simulation.io.MenuTerm;

import java.util.Arrays;
import java.util.List;

public class InputManager {
  public PackageSetup inputForSymulatorSetup() {
    MenuTerm term = new MenuTerm();
    ChoiceOptionManager optionManager = new ChoiceOptionManager();
    List<String> options = Arrays.asList(
        "Start Simulation",
        "Add Objects and other For Simulation",
        "End");
    int ind;

    while (true) {
      term.clear();
      ind = term.choiseOption(options);

      switch (ind) {
        case 0:
          if (optionManager.getPack().checkValid()) {
            return optionManager.getPack();
          } else {
            term.clear();
            term.showMsg("Fuck");
          }
        case 1:
          optionManager.choiseAction(term);
        case 2:
          return null;
      }

    }

  }

}
