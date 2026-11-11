package com.cosmos.simulation.physics.calculation;

import com.cosmos.simulation.math.Vector3;
import com.cosmos.simulation.model.ObjectCosmos;

public interface IAccelerationCalculate {

  Vector3 calculationg(ObjectCosmos obj1, ObjectCosmos obj2);
}
