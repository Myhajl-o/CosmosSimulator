package com.cosmos.simulation;

import com.cosmos.simulation.io.input.InputManager;
import com.cosmos.simulation.io.output.OutputManager;
import com.cosmos.simulation.engine.SimulationLauncher;
import com.cosmos.simulation.setup.PackegeSetup;

public class Main {
  public static void main(String argc[]) {
    InputManager input = new InputManager();
    PackegeSetup pack = input.inputForSymulatorSetup();
    SimulationLauncher launch = new SimulationLauncher();
    launch.startSimulation(pack.getSimSetup());
    OutputManager output = new OutputManager();
    output.outputResultSimulation(pack.getOutput(), pack.getSimSetup().cosmWorld);
  }
}
