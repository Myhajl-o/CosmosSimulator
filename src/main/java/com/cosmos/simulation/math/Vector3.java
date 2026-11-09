package com.cosmos.simulation.math;

import java.math.BigDecimal;

public class Vector3 {
  public BigDecimal x;
  public BigDecimal y;
  public BigDecimal z;

  public Vector3() {
    this.x = BigDecimal.ZERO;
    this.y = BigDecimal.ZERO;
    this.z = BigDecimal.ZERO;
  }

  public Vector3(BigDecimal x, BigDecimal y, BigDecimal z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
}
