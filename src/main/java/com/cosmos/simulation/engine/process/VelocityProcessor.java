package com.cosmos.simulation.engine.process;

import com.cosmos.simulation.engine.SimulationProcess;
import com.cosmos.simulation.model.CosmosWorld;
import com.cosmos.simulation.model.ObjectCosmos;
import com.cosmos.simulation.physics.calculation.IMoveVelocityCalculate;
import com.cosmos.simulation.physics.calculation.VelocityCalculate;
import com.cosmos.simulation.physics.update.IUpdatorVector3;
import com.cosmos.simulation.physics.update.VelocityUpdate;
import java.util.Objects;

public class VelocityProcessor implements SimulationProcess {
  private final IMoveVelocityCalculate calculator;
  private final IUpdatorVector3 updater;

  public VelocityProcessor() {
    this(new VelocityCalculate(), new VelocityUpdate());
  }

  public VelocityProcessor(IMoveVelocityCalculate calculator, IUpdatorVector3 updater) {
    this.calculator = Objects.requireNonNull(calculator);
    this.updater = Objects.requireNonNull(updater);
  }

  @Override
  public void processing(CosmosWorld cosmWorld, double time) {
    for (ObjectCosmos object : cosmWorld.getAllObj()) {
      updater.update(object, calculator.calculating(object, time));
    }
  }
}
