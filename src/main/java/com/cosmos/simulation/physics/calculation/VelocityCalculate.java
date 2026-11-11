package com.cosmos.simulation.physics.calculation;

import java.math.BigDecimal;

import com.cosmos.simulation.math.Vector3;
import com.cosmos.simulation.model.ObjectCosmos;
import com.cosmos.simulation.physics.Constants;

public class VelocityCalculate implements IMoveVelocityCalculate {

  @Override
  public Vector3 calculating(ObjectCosmos obj, double time) {
    Vector3 a = obj.getAcceleration();
    Vector3 V = obj.getVelocity();
    BigDecimal bigTime = BigDecimal.valueOf(time);
    BigDecimal lower = Constants.BoundariesSpace.lowerSpeedLight;
    BigDecimal upper = Constants.BoundariesSpace.upperSpeedLight.subtract(BigDecimal.ONE);

    return new Vector3(
        V.x.add(a.x.multiply(bigTime)).max(lower).min(upper),
        V.y.add(a.y.multiply(bigTime)).max(lower).min(upper),
        V.z.add(a.z.multiply(bigTime)).max(lower).min(upper));
  }
}
