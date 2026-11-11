package com.cosmos.simulation.factory;

import com.cosmos.simulation.model.ObjectCosmos;

import com.cosmos.simulation.io.input.model.Temp;

public interface CreatingObject {
  ObjectCosmos create(Temp temp);
}
