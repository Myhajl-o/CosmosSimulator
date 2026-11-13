package com.cosmos.simulation.io.input.process;

import java.util.ArrayList;
import java.util.List;

import com.cosmos.simulation.engine.SimulationProcess;
import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.io.input.InputProcesses;
import com.cosmos.simulation.physics.Constants;

public class InputFromTermProcess implements InputProcesses {

  @Override
  public List<SimulationProcess> inputProc(MenuTerm term) {

    List<SimulationProcess> processes = new ArrayList<>();
    int ind;

    while (true) {
      term.clear();
      ind = term.choiseOption(Constants.Massanges.inProc);
      if (ind == 3) {
        return processes;
      }
      processes.add(Constants.ObjForInput.factorProc.get(ind).get());
    }

  }
}
