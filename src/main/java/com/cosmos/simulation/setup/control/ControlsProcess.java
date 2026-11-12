package com.cosmos.simulation.setup.control;

import java.util.List;
import java.util.Arrays;

import com.cosmos.simulation.engine.SimulationProcess;
import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.io.input.InputProcesses;
import com.cosmos.simulation.io.input.process.InputFromTermProcess;

public class ControlsProcess implements Controls<List<SimulationProcess>> {
  public List<SimulationProcess> control(MenuTerm term) {
    List<String> option = Arrays.asList(
        "Add processes",
        "Delete process",
        "Back");
    int ind, i;
    List<SimulationProcess> processes = null;

    while (true) {
      term.clear();
      ind = term.choiseOption(option);
      switch (ind) {
        case 0:
          InputProcesses inProc = new InputFromTermProcess();
          processes = inProc.inputProc(term);

          break;
        case 1:
          String indDel = "Enter index proc for Delete: ";
          term.showMsg(indDel);
          i = Integer.parseInt(term.getInput());
          processes.remove(i);
          break;
        case 2:
          return processes;
      }
    }

  }
}
