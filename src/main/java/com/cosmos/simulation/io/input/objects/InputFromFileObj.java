package com.cosmos.simulation.io.input.objects;

import com.cosmos.simulation.io.MainInput;
import com.cosmos.simulation.io.MainInputFile;
import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.io.input.InputPath;
import com.cosmos.simulation.io.input.model.Temp;
import com.cosmos.simulation.model.ObjectCosmos;
import com.cosmos.simulation.io.input.InputObjects;
import com.cosmos.simulation.io.input.model.VecTemp;
import com.cosmos.simulation.validation.IdentifyObj;
import com.cosmos.simulation.validation.ValidationCheck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputFromFileObj implements InputObjects {

  private List<Temp> createTemps(List<String> param) {
    if (param.size() % 11 != 0) {
      return null;
    }
    List<Temp> temps = new ArrayList<>();
    for (int i = 0; i < param.size(); i += 11) {
      temps.add(new Temp(new VecTemp(param.get(i), param.get(i + 1), param.get(i + 2)),
          new VecTemp(param.get(i + 3), param.get(i + 4), param.get(i + 5)),
          new VecTemp(param.get(i + 6), param.get(i + 7), param.get(i + 8)),
          param.get(i + 9), param.get(i + 10)));

    }
    return temps;
  }

  public List<ObjectCosmos> inputObjcts(MenuTerm term) {
    if (term == null) {
      term = new MenuTerm();
    }

    String path = InputPath.input(term);
    MainInput file = new MainInputFile(path);
    List<ObjectCosmos> objects = new ArrayList<>();
    List<String> params;

    try {
      params = file.input();
      if (!ValidationCheck.checkObj(params)) {
        return null;
      }
      List<Temp> tempObjcts = createTemps(params);
      for (Temp temp : tempObjcts) {
        objects.add(IdentifyObj.identify(temp));
      }
      return objects;
    } catch (IOException e) {
      return null;
    }
  }
}
