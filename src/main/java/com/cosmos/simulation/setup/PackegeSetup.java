package com.cosmos.simulation.setup;

public class PackegeSetup {
  SimulationSetup simSetup;
  Output output;

  boolean checkValid() {
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
}
