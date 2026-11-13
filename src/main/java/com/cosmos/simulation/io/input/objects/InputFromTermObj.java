package com.cosmos.simulation.io.input.objects;

import com.cosmos.simulation.validation.ValidationCheck;
import com.cosmos.simulation.io.MainInputTerm;
import com.cosmos.simulation.io.MainInput;
import com.cosmos.simulation.io.input.InputObject;
import com.cosmos.simulation.io.input.model.VecTemp;
import com.cosmos.simulation.io.input.model.Temp;
import com.cosmos.simulation.model.ObjectCosmos;
import com.cosmos.simulation.physics.Constants;
import com.cosmos.simulation.validation.IdentifyObj;

import java.io.IOException;
import java.util.List;

public class InputFromTermObj implements InputObject {
  public ObjectCosmos inputObj() {

    MainInput input = new MainInputTerm();

    try {
      List<String> param = input.input(Constants.Massanges.inCosObj);
      if (!ValidationCheck.checkObj(param)) {
        return null;
      }

      Temp objTemp = new Temp(
          new VecTemp(param.get(0), param.get(1), param.get(2)),
          new VecTemp(param.get(3), param.get(4), param.get(5)),
          new VecTemp(param.get(6), param.get(7), param.get(8)),
          param.get(9), param.get(10));

      return IdentifyObj.identify(objTemp);

    } catch (IOException e) {
      return null;
    }

  }

}
