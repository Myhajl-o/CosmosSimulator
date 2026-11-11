package com.cosmos.simulation.model;

import java.util.List;
import java.util.ArrayList;

public class CosmosWorld {

  private final List<ObjectCosmos> allObj = new ArrayList<>();

  public void addObj(ObjectCosmos obj) {
    this.allObj.add(obj);
  }

  public void addObjects(List<ObjectCosmos> obj) {
    this.allObj.addAll(obj);
  }

  public ObjectCosmos getObj(int ind) {
    return this.allObj.get(ind);
  }

  public List<ObjectCosmos> getAllObj() {
    return this.allObj;
  }

  public void delete(int ind) {
    this.allObj.remove(ind);
  }
}
