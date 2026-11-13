package com.cosmos.simulation.io.input.time;

import com.cosmos.simulation.io.MenuTerm;
import com.cosmos.simulation.io.input.InputTime;
import com.cosmos.simulation.model.Time;
import java.util.concurrent.ThreadLocalRandom;

public class InputFromRandomTime implements InputTime {

  @Override
  public Time inputTime(MenuTerm term) {
    ThreadLocalRandom random = ThreadLocalRandom.current();
    long stepCount = random.nextLong(1, 100001);
    double timeStep = random.nextInt(1, 31556953);
    return new Time(stepCount, timeStep);
  }
}
