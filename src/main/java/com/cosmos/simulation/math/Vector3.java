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

  public void add(Vector3 other) {
    this.x = this.x.add(other.x);
    this.y = this.y.add(other.y);
    this.z = this.z.add(other.z);
  }

  public void sub(Vector3 other) {
    this.x = this.x.subtract(other.x);
    this.y = this.y.subtract(other.y);
    this.z = this.z.subtract(other.z);
  }

}
