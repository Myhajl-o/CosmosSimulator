package com.cosmos.simulation.physics;

import java.util.List;
import com.cosmos.simulation.factory.CreatingObject;
import com.cosmos.simulation.factory.CreatingBlackHole;
import com.cosmos.simulation.factory.CreatingStar;
import com.cosmos.simulation.io.input.objects.InputFromTermObj;
import com.cosmos.simulation.setup.control.ControlsObjects;
import com.cosmos.simulation.io.input.InputObject;
import com.cosmos.simulation.setup.control.Controls;
import com.cosmos.simulation.setup.control.ControlsObjects;
import com.cosmos.simulation.io.input.objects.InputFromRandomObj;
import com.cosmos.simulation.io.input.objects.InputFromDefaultObj;

import com.cosmos.simulation.factory.CreatingPlanet;
import java.math.BigDecimal;

public class Constants {

  /*
   * public class CollisionRules {
   * private Map<RulesCalculateCollision> rules;
   * 
   * public void setRules(CosmosWorld cosmWorld) {
   * 
   * }
   * 
   * public Map<RulesCalculateCollision> getRules() {
   * 
   * }
   * }
   */

  public static class BoundariesSpace {
    public static final BigDecimal upperCosm = new BigDecimal("1E27");
    public static final BigDecimal lowerCosm = new BigDecimal("-1E27");

    public static final BigDecimal upperSpeedLight = new BigDecimal("299792458");
    public static final BigDecimal lowerSpeedLight = new BigDecimal("-299792457");

    // Planets
    public static final BigDecimal upperMassPlanet = new BigDecimal("2.47E28");
    public static final BigDecimal lowerMassPlanet = new BigDecimal("1.19E23");

    public static final BigDecimal upperRadiusPlanet = new BigDecimal("5.38E8");
    public static final BigDecimal lowerRadiusPlanet = new BigDecimal("1.26E6");

    public static final BigDecimal upperDensityPlanet = new BigDecimal("14100");
    public static final BigDecimal lowerDensityPlanet = new BigDecimal("38");

    // Stars
    public static final BigDecimal upperMassStar = new BigDecimal("4.63E32");
    public static final BigDecimal lowerMassStar = new BigDecimal("1.49E29");

    public static final BigDecimal upperRadiusStar = new BigDecimal("2.23E12");
    public static final BigDecimal lowerRadiusStar = new BigDecimal("9.86E8");

    public static final BigDecimal upperDensityStar = new BigDecimal("37");
    public static final BigDecimal lowerDensityStar = new BigDecimal("1E-5");

    // Black holes: effective model radius, not event-horizon radius
    public static final BigDecimal upperMassBlackHole = new BigDecimal("1.31E41");
    public static final BigDecimal lowerMassBlackHole = new BigDecimal("7.56E30");

    public static final BigDecimal upperRadiusBlackHole = new BigDecimal("1.31E12");
    public static final BigDecimal lowerRadiusBlackHole = new BigDecimal("1.12E4");

    public static final BigDecimal upperDensityBlackHole = new BigDecimal("1.28E18");
    public static final BigDecimal lowerDensityBlackHole = new BigDecimal("14101");

  }

  public static class Geometry {
    public static final BigDecimal pi = new BigDecimal("3.141592");
  }

  public static class Physic {
    public static BigDecimal gravitati = new BigDecimal("6.67430E-11");
  }

  public static class CreateForParam {
    public static final List<CreatingObject> creaters = List.of(
        new CreatingBlackHole(),
        new CreatingStar(),
        new CreatingPlanet());
  }

  public static class AllListsInput {
    public static List<InputObject> inputObject = List.of(
        new InputFromTermObj(),
        new InputFromRandomObj(),
        new InputFromDefaultObj());
    /*
     * public static List<InputObjects> inputObjects = List.of(
     * new InputFromJSONObj(),
     * new InputFromFileObj(),
     * new InputFromBDObj()
     * );
     */
  }

  public static class ControlsList {
    public static List<Controls<?>> controls = List.of(
        new ControlsObjects()/*
                              * ,
                              * new ConstantsTime(),
                              * new ConstantsProcess(),
                              * new ConstantsMethodOutput()
                              */);
  }

}
