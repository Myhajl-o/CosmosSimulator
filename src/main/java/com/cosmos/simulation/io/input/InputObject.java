package com.cosmos.simulation.io.input;

import com.cosmos.simulation.model.ObjectCosmos;
import com.cosmos.simulation.io.MenuTerm;

public interface InputObject {
  ObjectCosmos inputObj(MenuTerm term);
}
