package com.cosmos.simulation.io.input;

import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.model.ObjectCosmos;
import java.util.List;

public interface InputObjects {
  List<ObjectCosmos> inputObjcts(MenuTerm term);
}
