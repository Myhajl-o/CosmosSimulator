package com.cosmos.simulation.io.input.model;

import com.cosmos.simulation.io.input.model.VecTemp;

public class Temp {
  public VecTemp coord;
  public VecTemp V;
  public VecTemp a;
  public String mass;
  public String radius;

  public Temp() {
    this.coord = new VecTemp();
    this.V = new VecTemp();
    this.a = new VecTemp();
    this.mass = "0";
    this.radius = "0";
  }

  public Temp(VecTemp coord, VecTemp V, VecTemp a, String mass, String radius) {
    this.coord = coord;
    this.V = V;
    this.a = a;
    this.mass = mass;
    this.radius = radius;
  }
}
