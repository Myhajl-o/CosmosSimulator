package com.cosmos.simulation.io;

import java.util.List;

import com.cosmos.simulation.model.ObjectCosmos;

public interface MainOutput {

  void outputObj(List<ObjectCosmos> objects, String path);
}
