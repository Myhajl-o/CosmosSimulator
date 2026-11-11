package com.cosmos.simulation.factory;

import com.cosmos.simulation.model.ObjectCosmos;
import com.cosmos.simulation.model.Star;
import com.cosmos.simulation.math.Vector3;
import com.cosmos.simulation.io.input.model.Temp;
import com.cosmos.simulation.physics.PhysicsFormulas;
import com.cosmos.simulation.physics.Constants;
import java.math.BigDecimal;

public class CreatingStar implements CreatingObject {

  @Override
  public ObjectCosmos create(Temp temp) {
    BigDecimal objDensity = PhysicsFormulas.averageDensity(new BigDecimal(temp.mass),
        new BigDecimal(temp.radius));

    if (objDensity.compareTo(Constants.BoundariesSpace.upperDensityStar) <= 0 &&
        objDensity.compareTo(Constants.BoundariesSpace.lowerDensityStar) >= 0) {
      ObjectCosmos obj = new Star(
          new Vector3(
              new BigDecimal(temp.coord.x),
              new BigDecimal(temp.coord.y),
              new BigDecimal(temp.coord.z)),
          new Vector3(
              new BigDecimal(temp.V.x),
              new BigDecimal(temp.V.y),
              new BigDecimal(temp.V.z)),
          new Vector3(
              new BigDecimal(temp.a.x),
              new BigDecimal(temp.a.y),
              new BigDecimal(temp.a.z)),
          new BigDecimal(temp.mass),
          new BigDecimal(temp.radius));
      return obj;
    }

    return null;
  }
}
