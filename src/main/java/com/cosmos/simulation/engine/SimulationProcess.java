package com.cosmos.simulation.engine;

import com.cosmos.simulation.model.CosmosWorld;

public interface SimulationProcess {

  void processing(CosmosWorld cosmWorld, double time);
}
