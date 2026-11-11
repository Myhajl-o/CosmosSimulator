package com.cosmos.simulation.io.output;

import com.cosmos.simulation.io.MainOutputFile;
import com.cosmos.simulation.model.CosmosWorld;

public class OutputToFile implements Output {
  private final String path;
  private final MainOutputFile output = new MainOutputFile();

  public OutputToFile(String path) {
    this.path = path;
  }

  @Override
  public void output(CosmosWorld cosmWorld) {
    output.outputObj(cosmWorld.getAllObj(), path);
  }
}
