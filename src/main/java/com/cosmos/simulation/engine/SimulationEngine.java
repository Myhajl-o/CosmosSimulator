package com.cosmos.simulation.engine;

import java.util.List;

import com.cosmos.simulation.model.CosmosWorld;
import com.cosmos.simulation.model.Time;

public class SimulationEngine {

  private List<SimulationProcess> processes;
  private CosmosWorld cosmWorld;
  private Time time;

  public void setProcesses(List<SimulationProcess> processes) {
    this.processes = processes;
  }

  public void setCosmWorld(CosmosWorld cosmWorld) {
    this.cosmWorld = cosmWorld;
  }

  public void setTime(Time time) {
    this.time = time;
  }

  public void conveyorEvents() {
    int i;
    for (i = 0; i < time.getStepCount(); i++) {
      for (SimulationProcess proc : processes) {
        proc.processing(cosmWorld, time.getTimeStep());
      }
    }
  }

}
