package com.cosmos.simulation.io.input.objects;

import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.io.input.InputObject;
import com.cosmos.simulation.io.input.model.Temp;
import com.cosmos.simulation.io.input.model.VecTemp;
import com.cosmos.simulation.model.ObjectCosmos;
import com.cosmos.simulation.physics.Constants;
import com.cosmos.simulation.physics.Constants.BoundariesSpace;
import com.cosmos.simulation.validation.IdentifyObj;
import com.cosmos.simulation.validation.ValidationCheck;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Creates objects from the midpoints of the selected type's parameter bounds.
 */
public class InputFromDefaultObj implements InputObject {
  private static final BigDecimal INDENT_STEP = new BigDecimal("100000000");

  private MenuTerm menu;
  private BigDecimal indent = BigDecimal.ZERO;

  /** Opens the terminal lazily when input is first requested. */
  public InputFromDefaultObj() {
  }

  /** Reuses the application's terminal menu. */
  public InputFromDefaultObj(MenuTerm menu) {
    this.menu = Objects.requireNonNull(menu);
  }

  /**
   * Increases the coordinate offset only after successful validation and
   * identification.
   */
  @Override
  public ObjectCosmos inputObj() {
    if (menu == null) {
      menu = new MenuTerm();
    }
    int choice = menu.choiseOption(Constants.Massanges.defObj);
    BigDecimal mass;
    BigDecimal radius;
    switch (choice) {
      case 0 -> {
        mass = midpoint(BoundariesSpace.lowerMassPlanet, BoundariesSpace.upperMassPlanet);
        radius = midpoint(BoundariesSpace.lowerRadiusPlanet, BoundariesSpace.upperRadiusPlanet);
      }
      case 1 -> {
        mass = midpoint(BoundariesSpace.lowerMassStar, BoundariesSpace.upperMassStar);
        radius = midpoint(BoundariesSpace.lowerRadiusStar, BoundariesSpace.upperRadiusStar);
      }
      case 2 -> {
        mass = midpoint(BoundariesSpace.lowerMassBlackHole, BoundariesSpace.upperMassBlackHole);
        radius = midpoint(BoundariesSpace.lowerRadiusBlackHole, BoundariesSpace.upperRadiusBlackHole);
      }
      default -> throw new IllegalStateException("Unknown object type selection");
    }

    BigDecimal coordinate = midpoint(BoundariesSpace.lowerCosm, BoundariesSpace.upperCosm);
    String velocity = midpoint(BoundariesSpace.lowerSpeedLight, BoundariesSpace.upperSpeedLight)
        .toPlainString();
    Temp temp = new Temp(
        new VecTemp(coordinate.add(indent).toPlainString(), coordinate.toPlainString(), coordinate.toPlainString()),
        new VecTemp(velocity, velocity, velocity),
        new VecTemp("0", "0", "0"),
        mass.toPlainString(), radius.toPlainString());

    List<String> parameters = List.of(
        temp.coord.x, temp.coord.y, temp.coord.z,
        temp.V.x, temp.V.y, temp.V.z,
        temp.a.x, temp.a.y, temp.a.z, temp.mass, temp.radius);
    if (!ValidationCheck.checkObj(parameters)) {
      return null;
    }
    ObjectCosmos object = IdentifyObj.identify(temp);
    if (object != null) {
      indent = indent.add(INDENT_STEP);
    }
    return object;
  }

  private static BigDecimal midpoint(BigDecimal lower, BigDecimal upper) {
    return lower.add(upper).divide(BigDecimal.TWO);
  }
}
