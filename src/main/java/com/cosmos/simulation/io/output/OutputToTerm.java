package com.cosmos.simulation.io.output;

import com.cosmos.simulation.io.MainOutputTerm;
import com.cosmos.simulation.io.MainOutput;
import com.cosmos.simulation.model.CosmosWorld;

public class OutputToTerm implements Output {

  public void output(CosmosWorld cosmWorld) {
    MainOutput out = new MainOutputTerm();
    out.outputObj(cosmWorld.getAllObj(), null);
  }
}
