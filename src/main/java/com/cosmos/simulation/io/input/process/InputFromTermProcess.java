package com.cosmos.simulation.io.input.process;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.cosmos.simulation.engine.SimulationProcess;
import com.cosmos.simulation.engine.process.AccelerationProcessor;
import com.cosmos.simulation.engine.process.MoveProcessor;
import com.cosmos.simulation.engine.process.VelocityProcessor;
import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.io.input.InputProcesses;

public class InputFromTermProcess implements InputProcesses {

  @Override
  public List<SimulationProcess> inputProc(MenuTerm term) {
    List<Supplier<SimulationProcess>> factories = List.of(
        AccelerationProcessor::new,
        VelocityProcessor::new,
        MoveProcessor::new);

    List<SimulationProcess> processes = new ArrayList<>();

    List<String> msg = List.of(
        "Add AccelerationProcessor",
        "Add VelocityProcessor",
        "Add MoveProcessor",
        "Back");
    int ind;

    while (true) {
      term.clear();
      ind = term.choiseOption(msg);
      if (ind == factories.size()) {
        return processes;
      }
      processes.add(factories.get(ind).get());
    }

  }
}
