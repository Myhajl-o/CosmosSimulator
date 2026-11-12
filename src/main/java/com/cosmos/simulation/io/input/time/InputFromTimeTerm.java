package com.cosmos.simulation.io.input.time;

import java.util.List;
import java.util.ArrayList;

import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.io.input.InputTime;
import com.cosmos.simulation.model.Time;
import com.cosmos.simulation.validation.ValidationCheck;

public class InputFromTimeTerm implements InputTime {

  public Time CreateTime(List<String> param)
      throws NumberFormatException {
    long tempL;
    double tempD;

    try {
      tempL = Long.parseLong(param.get(0).strip());
      tempD = Double.parseDouble(param.get(1).strip());

      if (!Double.isFinite(tempD)) {
        throw new NumberFormatException();
      }

      return new Time(tempL, tempD);

    } catch (NumberFormatException e) {
      throw e;
    }

  }

  @Override
  public Time inputTime(MenuTerm term) {
    List<String> msg = List.of(
        "Enter Step Count: ",
        "Enter Time Step: ");
    List<String> param = new ArrayList<>();
    for (String prompt : msg) {
      term.showMsg(prompt);
      param.add(term.getInput());
    }

    try {
      if (!ValidationCheck.checkObj(param)) {
        return null;
      }
      return CreateTime(param);

    } catch (NumberFormatException e) {
      return null;
    }
  }
}
