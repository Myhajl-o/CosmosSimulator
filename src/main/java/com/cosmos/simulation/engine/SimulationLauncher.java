package com.cosmos.simulation.engine;

import com.cosmos.simulation.setup.SimulationSetup;

public class SimulationLauncher {

  public void startSimulation(SimulationSetup start) {
    SimulationEngine engine = new SimulationEngine();
    engine.setCosmWorld(start.cosmWorld);
    engine.setTime(start.time);
    engine.setProcesses(start.proc);
    engine.conveyorEvents();
  }
}
