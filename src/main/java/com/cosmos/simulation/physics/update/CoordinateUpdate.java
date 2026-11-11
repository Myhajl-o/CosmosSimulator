package com.cosmos.simulation.physics.update;

import com.cosmos.simulation.math.Vector3;
import com.cosmos.simulation.model.ObjectCosmos;

public class CoordinateUpdate implements IUpdatorVector3 {

  public void update(ObjectCosmos obj, Vector3 param) {
    obj.addCoord(param);
  }
}
