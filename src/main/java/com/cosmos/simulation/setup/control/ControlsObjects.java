package com.cosmos.simulation.setup.control;

import com.cosmos.simulation.model.CosmosWorld;
import com.cosmos.simulation.model.ObjectCosmos;
import com.cosmos.simulation.physics.Constants;
import com.cosmos.simulation.io.MenuTerm;
//import com.cosmos.simulation.io.input.InputPath;
import java.util.List;
import java.util.Arrays;

public class ControlsObjects implements Controls<CosmosWorld> {
  private CosmosWorld cosmWorld;

  public CosmosWorld control(MenuTerm term) {
    List<String> options = Arrays.asList(
        "Add Object",
        "Add Objects",
        "Show Objects",
        "Delete Object",
        "Back");
    if (term == null) {
      term = new MenuTerm();
    }
    int ind, i;

    while (true) {
      term.clear();
      ind = term.choiseOption(options);
      switch (ind) {
        case 0:
          term.clear();
          List<String> optionsObj = Arrays.asList(
              "Add from Terminal",
              "Randomly Generated Object",
              "Default Object",
              "Back");
          i = term.choiseOption(optionsObj);
          ObjectCosmos obj = Constants.AllListsInput.inputObject.get(i).inputObj();
          cosmWorld.addObj(obj);
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

        case 3:
          String msgFordel = "Enter index object for delete: ";
          term.showMsg(msgFordel);
          i = Integer.parseInt(term.getInput());
          cosmWorld.delete(i);
        case 4:
          term.clear();
          return cosmWorld;
      }

    }
  }
}
