package com.cosmos.simulation;

import com.cosmos.simulation.io.input.InputManager;
import com.cosmos.simulation.io.output.OutputManager;
import com.cosmos.simulation.engine.SimulationLauncher;
import com.cosmos.simulation.setup.PackegeSetup;

public class Main {
  private final static InputManager input = new InputManager();
  private final static SimulationLauncher launch = new SimulationLauncher();
  private final static OutputManager output = new OutputManager();

  public static void main(String argc[]) {
    PackegeSetup pack = input.inputForSymulatorSetup();
    launch.startSimulation(pack.getSimSetup());
    output.outputResultSimulation(pack.getOutput(), pack.getSimSetup().cosmWorld);
  }
}
