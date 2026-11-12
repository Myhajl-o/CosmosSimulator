package com.cosmos.simulation.setup;

import com.cosmos.simulation.engine.SimulationProcess;
import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.io.output.Output;
import com.cosmos.simulation.model.CosmosWorld;
import com.cosmos.simulation.model.Time;
import com.cosmos.simulation.setup.control.Controls;
import com.cosmos.simulation.setup.control.ControlsMethOutput;
import com.cosmos.simulation.setup.control.ControlsObjects;
import com.cosmos.simulation.setup.control.ControlsProcess;
import com.cosmos.simulation.setup.control.ControlsTime;
import java.util.List;

public class ChoiceOptionManager {
  private final PackegeSetup pack = new PackegeSetup();
  private final Controls<CosmosWorld> objectsControl = new ControlsObjects();
  private final Controls<Time> timeControl = new ControlsTime();
  private final Controls<List<SimulationProcess>> processesControl = new ControlsProcess();
  private final Controls<Output> outputControl = new ControlsMethOutput();

  public PackegeSetup getPack() {
    return this.pack;
  }

  public PackegeSetup choiseAction(MenuTerm term) {
    if (term == null) {
      term = new MenuTerm();
    }
    List<String> options = List.of("Objects", "Time", "Processes", "Output", "Back");

    while (true) {
      term.clear();
      int ind = term.choiseOption(options);
      switch (ind) {
        case 0:
          CosmosWorld cosmWorld = objectsControl.control(term);
          if (cosmWorld != null) {
            pack.getSimSetup().cosmWorld = cosmWorld;
          }
          break;
        case 1:
          Time time = timeControl.control(term);
          if (time != null) {
            pack.getSimSetup().time = time;
          }
          break;
        case 2:
          List<SimulationProcess> processes = processesControl.control(term);
          if (processes != null) {
            pack.getSimSetup().proc = processes;
          }
          break;
        case 3:
          Output output = outputControl.control(term);
          if (output != null) {
            pack.setOutput(output);
          }
          break;
        case 4:
          term.clear();
          return pack;
      }
    }
  }
}
