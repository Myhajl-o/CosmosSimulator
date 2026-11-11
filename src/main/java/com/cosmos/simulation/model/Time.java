package com.cosmos.simulation.model;

public class Time {

  private long stepCount;
  private double timeStep;

  public void setStepCount(long stepCount) {
    this.stepCount = stepCount;
  }

  public void setTimeStep(double timeStep) {
    this.timeStep = timeStep;
  }

  public long getStepCount() {
    return this.stepCount;
  }

  public double getTimeStep() {
    return this.timeStep;
  }
}
