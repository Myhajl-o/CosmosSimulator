package com.cosmos.simulation.physics.update;

import com.cosmos.simulation.math.Vector3;
import com.cosmos.simulation.model.ObjectCosmos;

public interface IUpdatorVector3 {

  void update(ObjectCosmos obj, Vector3 param);
}
