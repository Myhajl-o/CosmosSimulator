package com.cosmos.simulation.validation;

import java.math.BigDecimal;
import java.util.List;

public class ValidationCheck {
  public static boolean checkObj(List<String> param) {

    for (String p : param) {
      if (p == null || p.isBlank()) {
        return false;
      }
      p.trim();

      try {
        new BigDecimal(p.strip());
      } catch (NumberFormatException exception) {
        return false;
      }
    }
    return true;
  }
}
