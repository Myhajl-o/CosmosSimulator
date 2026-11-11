package com.cosmos.simulation.physics.calculation;

import com.cosmos.simulation.math.Vector3;
import com.cosmos.simulation.model.ObjectCosmos;

public interface IMoveVelocityCalculate {

  Vector3 calculating(ObjectCosmos obj, double time);
}
