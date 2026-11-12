package com.cosmos.simulation.io.input;

import java.util.List;

import com.cosmos.simulation.engine.SimulationProcess;
import com.cosmos.simulation.io.MenuTerm;

public interface InputProcesses {

  List<SimulationProcess> inputProc(MenuTerm term);
}
