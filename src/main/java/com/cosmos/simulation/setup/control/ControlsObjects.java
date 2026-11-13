package com.cosmos.simulation.setup.control;

import com.cosmos.simulation.model.CosmosWorld;
import com.cosmos.simulation.model.ObjectCosmos;
import com.cosmos.simulation.physics.Constants;
import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.io.MainOutputTerm;
//import com.cosmos.simulation.io.input.InputPath;

public class ControlsObjects implements Controls<CosmosWorld> {
  private CosmosWorld cosmWorld = new CosmosWorld();

  public CosmosWorld control(MenuTerm term) {

    if (term == null) {
      term = new MenuTerm();
    }
    int ind, i;

    while (true) {
      term.clear();
      ind = term.choiseOption(Constants.Massanges.controlObj);
      switch (ind) {
        case 0:
          term.clear();
          i = term.choiseOption(Constants.Massanges.controlCreateObj);
          ObjectCosmos obj = Constants.AllListsInput.inputObject.get(i).inputObj();
          cosmWorld.addObj(obj);
          break;
        /*
         * case 1:
         * term.clear();
         * List<String> optionsObjcts = Arrays.asList(
         * "Add Objects from JSON",
         * "Add Objects from File",
         * "Add Objects from BD");
         * 
         * i = term.choiseOption(optionsObjcts);
         * String path = InputPath.input(term);
         */
        case 2:
          term.clear();
          new MainOutputTerm().outputObj(cosmWorld.getAllObj(), null);
          term.showMsg("Press Enter to continue...");
          term.getInput();
          break;
        case 3:
          term.showMsg("Enter index object for delete: ");
          i = Integer.parseInt(term.getInput());
          cosmWorld.delete(i);
          break;
        case 4:
          term.clear();
          return cosmWorld;
      }

    }
  }
}
