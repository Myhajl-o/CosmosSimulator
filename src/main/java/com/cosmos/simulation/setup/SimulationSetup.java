package com.cosmos.simulation.setup;

import java.util.List;
import com.cosmos.simulation.engine.SimulationProcess;
import com.cosmos.simulation.model.Time;

import com.cosmos.simulation.model.CosmosWorld;

public class SimulationSetup {

  public CosmosWorld cosmWorld = new CosmosWorld();
  public Time time;
  public List<SimulationProcess> proc;
}
