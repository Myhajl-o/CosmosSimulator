package com.cosmos.simulation.model;

import com.cosmos.simulation.math.Vector3;
import java.math.BigDecimal;

public class Star extends ObjectCosmos {

  public Star(Vector3 coord, Vector3 V, Vector3 a, BigDecimal mass, BigDecimal radius) {
    super(coord, V, a, mass, radius);
  }
}
