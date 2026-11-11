package com.cosmos.simulation.setup;

import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.model.CosmosWorld;
import com.cosmos.simulation.model.Time;
import com.cosmos.simulation.physics.Constants;
import com.cosmos.simulation.setup.PackegeSetup;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Objects;

public class ChoiceOptionManager {

  private PackegeSetup pack;

  public PackegeSetup getPack(){
    this.pack;
  }

  public PackegeSetup choiseAction(MenuTerm term) {
    if (term == null) {
      term = new MenuTerm();
    }
    List<String> options = Arrays.asList(
        "Create Object",
        "Create Objects",
        "Set Time",
        "Add Processes",
        "Add Method Output");

    while (true) {
      term.clear();
      int ind = term.choiseOption(options);

      switch (ind) {
        case 0:
          CosmosWorld cosmWorld = Constants.ControlsList.controls.get(ind).control(term);
          this.pack.simSetup.cosmWorld.addObjects(cosmWorld.getAllObj());

        case 1:
          Time time = Constants.ControlsList.controls.get(ind).control(term);
          this.pack.simSetup.time = time;

        case 2:
          List<SimulationProcess> proc = Constants.ControlsList.controls.get(ind).control(term);
          this.pack.simSetup.proc = proc;

        case 3:
          Output out = Constants.ControlsList.controls.get(ind).control(term);
          this.pack.output = out;

        case 4:
          return this.pack;
      }
    }
  }
}
