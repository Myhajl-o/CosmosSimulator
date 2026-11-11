package com.cosmos.simulation.physics.calculation;

import com.cosmos.simulation.math.Vector3;
import com.cosmos.simulation.model.ObjectCosmos;
import com.cosmos.simulation.physics.PhysicsFormulas;
import java.math.BigDecimal;
import java.math.MathContext;

public class AccelerationCalculate implements IAccelerationCalculate {

  /** Returns the acceleration of obj1 towards obj2 without changing either object. */
  @Override
  public Vector3 calculationg(ObjectCosmos obj1, ObjectCosmos obj2) {
    Vector3 source = obj2.getCoord();
    Vector3 displacement = new Vector3(source.x, source.y, source.z);
    displacement.sub(obj1.getCoord());
    BigDecimal distanceSquared = displacement.x.pow(2)
        .add(displacement.y.pow(2))
        .add(displacement.z.pow(2));
    if (distanceSquared.signum() == 0) {
      throw new ArithmeticException("Acceleration is undefined for coincident objects");
    }
    BigDecimal distance = distanceSquared.sqrt(MathContext.DECIMAL128);
    BigDecimal acceleration = PhysicsFormulas.gravitationalAcceleration(obj2.getMass(), distance);

    return new Vector3(
        acceleration.multiply(displacement.x).divide(distance, MathContext.DECIMAL128),
        acceleration.multiply(displacement.y).divide(distance, MathContext.DECIMAL128),
        acceleration.multiply(displacement.z).divide(distance, MathContext.DECIMAL128));
  }
}
