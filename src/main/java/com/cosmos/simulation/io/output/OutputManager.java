package com.cosmos.simulation.io.output;

import com.cosmos.simulation.model.CosmosWorld;

public class OutputManager {
  public void outputResultSimulation(Output out, CosmosWorld cosmWorld) {
    out.output(cosmWorld);
  }
}
