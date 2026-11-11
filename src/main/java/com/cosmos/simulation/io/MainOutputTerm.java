package com.cosmos.simulation.io;

import java.util.List;

import com.cosmos.simulation.model.ObjectCosmos;

public class MainOutputTerm implements MainOutput {

  /** Prints object parameters to the terminal; path is unused for this output. */
  @Override
  public void outputObj(List<ObjectCosmos> objects, String path) {
    int i = 1;
    for (ObjectCosmos obj : objects) {
      System.out.println(i + ". --------");
      System.out.println("Coordinate: x:" + obj.getCoord().x
          + ",y:" + obj.getCoord().y + ",z:" + obj.getCoord().z);
      System.out.println("Velocity: x:" + obj.getVelocity().x
          + ",y:" + obj.getVelocity().y + ",z:" + obj.getVelocity().z);
      System.out.println("Acceleration: x:" + obj.getAcceleration().x
          + ",y:" + obj.getAcceleration().y + ",z:" + obj.getAcceleration().z);
      System.out.println("Mass: " + obj.getMass());
      System.out.println("Radius: " + obj.getRadius());
      i++;
    }
  }
}
