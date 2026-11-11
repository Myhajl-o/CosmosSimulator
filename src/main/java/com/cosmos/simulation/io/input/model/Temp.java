package com.cosmos.simulation.io.input.model;

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
    this.mass = null;
    this.radius = null;
  }

  public Temp(VecTemp coord, VecTemp V, VecTemp a, String mass, String radius) {
    this.coord = coord;
    this.V = V;
    this.a = a;
    this.mass = mass;
    this.radius = radius;
  }
}
