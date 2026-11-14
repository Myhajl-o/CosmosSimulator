package com.cosmos.simulation.io.input.objects;

import com.cosmos.simulation.io.input.InputObject;
import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.io.input.model.Temp;
import com.cosmos.simulation.io.input.model.VecTemp;
import com.cosmos.simulation.model.ObjectCosmos;
import com.cosmos.simulation.physics.Constants.BoundariesSpace;
import com.cosmos.simulation.validation.IdentifyObj;
import com.cosmos.simulation.validation.ValidationCheck;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.random.RandomGenerator;

/** Generates parameters across the combined bounds of all celestial object types. */
public class InputFromRandomObj implements InputObject {
  private final RandomGenerator random;

  public InputFromRandomObj() {
    this(RandomGenerator.getDefault());
  }

  public InputFromRandomObj(RandomGenerator random) {
    this.random = Objects.requireNonNull(random);
  }

  /** Returns the identified object, or null when the generated parameters are rejected. */
  @Override
  public ObjectCosmos inputObj(MenuTerm term) {
    BigDecimal minMass = BoundariesSpace.lowerMassPlanet
        .min(BoundariesSpace.lowerMassStar).min(BoundariesSpace.lowerMassBlackHole);
    BigDecimal maxMass = BoundariesSpace.upperMassPlanet
        .max(BoundariesSpace.upperMassStar).max(BoundariesSpace.upperMassBlackHole);
    BigDecimal minRadius = BoundariesSpace.lowerRadiusPlanet
        .min(BoundariesSpace.lowerRadiusStar).min(BoundariesSpace.lowerRadiusBlackHole);
    BigDecimal maxRadius = BoundariesSpace.upperRadiusPlanet
        .max(BoundariesSpace.upperRadiusStar).max(BoundariesSpace.upperRadiusBlackHole);

    Temp temp = new Temp(
        randomVector(BoundariesSpace.lowerCosm, BoundariesSpace.upperCosm),
        randomVector(BoundariesSpace.lowerSpeedLight, BoundariesSpace.upperSpeedLight),
        new VecTemp("0", "0", "0"),
        randomValue(minMass, maxMass),
        randomValue(minRadius, maxRadius));

    List<String> parameters = List.of(
        temp.coord.x, temp.coord.y, temp.coord.z,
        temp.V.x, temp.V.y, temp.V.z,
        temp.a.x, temp.a.y, temp.a.z, temp.mass, temp.radius);
    if (!ValidationCheck.checkObj(parameters)) {
      return null;
    }
    return IdentifyObj.identify(temp);
  }

  private VecTemp randomVector(BigDecimal lower, BigDecimal upper) {
    return new VecTemp(randomValue(lower, upper), randomValue(lower, upper), randomValue(lower, upper));
  }

  private String randomValue(BigDecimal lower, BigDecimal upper) {
    BigDecimal fraction = BigDecimal.valueOf(random.nextDouble());
    return lower.add(upper.subtract(lower).multiply(fraction)).toPlainString();
  }
}
