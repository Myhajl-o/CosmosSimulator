package com.cosmos.simulation.io.input;

import com.cosmos.simulation.setup.PackegeSetup;
import com.cosmos.simulation.setup.ChoiceOptionManager;
import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.physics.Constants;

public class InputManager {
  private final MenuTerm term = new MenuTerm();
  private final ChoiceOptionManager optionManager = new ChoiceOptionManager();

  public PackegeSetup inputForSymulatorSetup() {
    int ind;

    while (true) {
      term.clear();
      ind = term.choiseOption(Constants.Massanges.managerInOption);

      switch (ind) {
        case 0:
          if (optionManager.getPack().checkValid()) {
            return optionManager.getPack();
          }
          showTemporaryMessage("Complete the simulation settings before starting.");
          break;
        case 1:
          optionManager.choiseAction(term);
          break;
        case 2:
          System.exit(0);
          break;
      }
    }

  }

  private void showTemporaryMessage(String message) {
    term.clear();
    term.showMsg(message);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      System.exit(1);
    }
  }

}
