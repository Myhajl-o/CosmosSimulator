package com.cosmos.simulation.physics;

import java.util.List;
import java.util.Arrays;
import com.cosmos.simulation.factory.CreatingObject;
import com.cosmos.simulation.factory.CreatingBlackHole;
import com.cosmos.simulation.factory.CreatingStar;
import com.cosmos.simulation.factory.CreatingPlanet;
import java.util.Map;
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

  public class BoundariesSpace {
    BigDecimal upperCosm = new BigDecimal("1E27");
    BigDecimal lowerCosm = new BigDecimal("-1E27");

    BigDecimal upperSpeedLight = new BigDecimal("299792458");
    BigDecimal lowerSpeedLight = new BigDecimal("-299792457");

    // Planets
    BigDecimal upperMassPlanet = new BigDecimal("2.47E28");
    BigDecimal lowerMassPlanet = new BigDecimal("1.19E23");

    BigDecimal upperRadiusPlanet = new BigDecimal("1.53E8");
    BigDecimal lowerRadiusPlanet = new BigDecimal("1.97E6");

    BigDecimal upperDensityPlanet = new BigDecimal("1.41E4");
    BigDecimal lowerDensityPlanet = new BigDecimal("38");

    // Stars: excluding white dwarfs and neutron stars
    BigDecimal upperMassStar = new BigDecimal("4.63E32");
    BigDecimal lowerMassStar = new BigDecimal("1.49E29");

    BigDecimal upperRadiusStar = new BigDecimal("1E12");
    BigDecimal lowerRadiusStar = new BigDecimal("5.84E7");

    BigDecimal upperDensityStar = new BigDecimal("2E5");
    BigDecimal lowerDensityStar = new BigDecimal("1E-5");

    // Black holes: non-rotating, uncharged approximation
    BigDecimal upperMassBlackHole = new BigDecimal("1.31E41");
    BigDecimal lowerMassBlackHole = new BigDecimal("7.56E30");

    BigDecimal upperRadiusBlackHole = new BigDecimal("1.95E14");
    BigDecimal lowerRadiusBlackHole = new BigDecimal("1.12E4");

    // Conventional average density calculated using the horizon radius
    BigDecimal upperDensityBlackHole = new BigDecimal("1.28E18");
    BigDecimal lowerDensityBlackHole = new BigDecimal("4.23E-3");

  }

  public class CreateForParam {
    public static List<CreatingObject> rule = new Arrays.asList(
        new CreatingBlackHole(),
        new CreatingStar(),
        new CreatingPlanet());

  }
}
