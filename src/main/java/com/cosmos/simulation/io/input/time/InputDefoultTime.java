package com.cosmos.simulation.io.input.time;

import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.io.input.InputTime;
import com.cosmos.simulation.model.Time;

public class InputDefoultTime implements InputTime {

  @Override
  public Time inputTime(MenuTerm term) {
    return new Time(100, 3600);
  }
}
