package com.cosmos.simulation.setup;

import com.cosmos.simulation.io.output.Output;

public class PackegeSetup {
  private SimulationSetup simSetup = new SimulationSetup();
  private Output output;

  public boolean checkValid() {
    if (simSetup == null) {
      return false;
    }
    if (simSetup.cosmWorld == null) {
      return false;
    }
    if (simSetup.time == null) {
      return false;
    }
    if (simSetup.proc == null) {
      return false;
    }
    if (output == null) {
      return false;
    }

    return true;
  }

  public SimulationSetup getSimSetup() {
    return this.simSetup;
  }

  public Output getOutput() {
    return this.output;
  }

  public void setOutput(Output output) {
    this.output = output;
  }

}
