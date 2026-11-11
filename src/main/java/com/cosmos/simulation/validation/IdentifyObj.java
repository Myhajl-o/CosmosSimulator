package com.cosmos.simulation.validation;

import java.math.BigDecimal;

import com.cosmos.simulation.factory.CreatingObject;
import com.cosmos.simulation.io.input.model.Temp;
import com.cosmos.simulation.model.ObjectCosmos;
import com.cosmos.simulation.physics.Constants;

public class IdentifyObj {
  public static ObjectCosmos identify(Temp temp) {
    if (new BigDecimal(temp.coord.x).compareTo(Constants.BoundariesSpace.upperCosm) > 0 ||
        new BigDecimal(temp.coord.x).compareTo(Constants.BoundariesSpace.lowerCosm) < 0) {
      return null;
    }
    if (new BigDecimal(temp.coord.y).compareTo(Constants.BoundariesSpace.upperCosm) > 0 ||
        new BigDecimal(temp.coord.y).compareTo(Constants.BoundariesSpace.lowerCosm) < 0) {
      return null;
    }
    if (new BigDecimal(temp.coord.z).compareTo(Constants.BoundariesSpace.upperCosm) > 0 ||
        new BigDecimal(temp.coord.z).compareTo(Constants.BoundariesSpace.lowerCosm) < 0) {
      return null;
    }

    if (new BigDecimal(temp.V.x).compareTo(Constants.BoundariesSpace.upperSpeedLight) > 0 ||
        new BigDecimal(temp.V.x).compareTo(Constants.BoundariesSpace.lowerSpeedLight) < 0) {
      return null;
    }
    if (new BigDecimal(temp.V.y).compareTo(Constants.BoundariesSpace.upperSpeedLight) > 0 ||
        new BigDecimal(temp.V.y).compareTo(Constants.BoundariesSpace.lowerSpeedLight) < 0) {
      return null;
    }
    if (new BigDecimal(temp.V.z).compareTo(Constants.BoundariesSpace.upperSpeedLight) > 0 ||
        new BigDecimal(temp.V.z).compareTo(Constants.BoundariesSpace.lowerSpeedLight) < 0) {
      return null;
    }

    for (CreatingObject creater : Constants.CreateForParam.creaters) {
      ObjectCosmos obj = creater.create(temp);
      if (obj != null) {
        return obj;
      }
    }

    return null;
  }
}
