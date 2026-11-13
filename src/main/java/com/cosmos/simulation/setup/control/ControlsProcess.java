package com.cosmos.simulation.setup.control;

import java.util.List;

import com.cosmos.simulation.engine.SimulationProcess;
import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.io.input.InputProcesses;
import com.cosmos.simulation.io.input.process.InputFromTermProcess;
import com.cosmos.simulation.physics.Constants;

public class ControlsProcess implements Controls<List<SimulationProcess>> {
  public List<SimulationProcess> control(MenuTerm term) {

    int ind, i;
    List<SimulationProcess> processes = null;

    while (true) {
      term.clear();
      ind = term.choiseOption(Constants.Massanges.controlProc);
      switch (ind) {
        case 0:
          InputProcesses inProc = new InputFromTermProcess();
          processes = inProc.inputProc(term);

          break;
        case 1:
          term.showMsg("Enter index proc for Delete: ");
          i = Integer.parseInt(term.getInput());
          processes.remove(i);
          break;
        case 2:
          return processes;
      }
    }
  }
}
