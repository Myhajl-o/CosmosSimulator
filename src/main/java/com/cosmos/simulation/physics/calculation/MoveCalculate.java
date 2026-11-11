package com.cosmos.simulation.physics.calculation;

import java.math.BigDecimal;

import com.cosmos.simulation.math.Vector3;
import com.cosmos.simulation.model.ObjectCosmos;

public class MoveCalculate implements IMoveVelocityCalculate {

  @Override
  public Vector3 calculating(ObjectCosmos obj, double time) {
    BigDecimal bigTime = BigDecimal.valueOf(time);
    Vector3 V = obj.getVelocity();

    return new Vector3(
        V.x.multiply(bigTime),
        V.y.multiply(bigTime),
        V.z.multiply(bigTime));
  }
}
