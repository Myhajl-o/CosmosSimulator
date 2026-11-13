package com.cosmos.simulation.engine.process;

import com.cosmos.simulation.engine.SimulationProcess;
import com.cosmos.simulation.math.Vector3;
import com.cosmos.simulation.model.CosmosWorld;
import com.cosmos.simulation.model.ObjectCosmos;
import com.cosmos.simulation.physics.calculation.AccelerationCalculate;
import com.cosmos.simulation.physics.calculation.IAccelerationCalculate;
import com.cosmos.simulation.physics.update.AccelerationUpdate;
import com.cosmos.simulation.physics.update.IUpdatorVector3;
import java.util.List;
import java.util.Objects;

public class AccelerationProcessor implements SimulationProcess {
  private final IAccelerationCalculate calculator;
  private final IUpdatorVector3 updater;

  public AccelerationProcessor() {
    this(new AccelerationCalculate(), new AccelerationUpdate());
  }

  public AccelerationProcessor(IAccelerationCalculate calculator, IUpdatorVector3 updater) {
    this.calculator = Objects.requireNonNull(calculator);
    this.updater = Objects.requireNonNull(updater);
  }

  /**
   * Recomputes acceleration from current positions; time is not needed for this
   * step.
   */
  @Override
  public void processing(CosmosWorld cosmWorld, double time) {
    List<ObjectCosmos> objects = List.copyOf(cosmWorld.getAllObj());
    for (ObjectCosmos object : objects) {
      Vector3 total = new Vector3();
      for (ObjectCosmos other : objects) {
        if (object != other) {
          total.add(calculator.calculationg(object, other));
        }
      }
      updater.update(object, total);
    }
  }
}
