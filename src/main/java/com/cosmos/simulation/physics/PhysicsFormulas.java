package com.cosmos.simulation.physics;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PhysicsFormulas {

  public static BigDecimal gravitationalAcceleration(BigDecimal mass, BigDecimal distance) {
    // Exception

    return Constants.Physic.gravitati
        .multiply(mass)
        .divide(distance.pow(2), 7, RoundingMode.HALF_EVEN);
  }
  /*
   * BigDecimal sphereVolume(BigDecimal radius) {
   * 
   * }
   */

  public static BigDecimal averageDensity(BigDecimal mass, BigDecimal radius) {
    BigDecimal numerator = mass.multiply(new BigDecimal("3"));

    BigDecimal denominator = new BigDecimal("4")
        .multiply(Constants.Geometry.pi)
        .multiply(radius.pow(3));

    return numerator.divide(denominator, 7, RoundingMode.HALF_EVEN);
  }

}
