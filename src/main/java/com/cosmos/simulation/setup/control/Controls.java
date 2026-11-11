package com.cosmos.simulation.setup.control;

import com.cosmos.simulation.io.MenuTerm;

public interface Controls<T> {
  T control(MenuTerm term);
}
