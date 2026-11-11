package com.cosmos.simulation.model;

import java.math.BigDecimal;
import com.cosmos.simulation.math.Vector3;

public abstract class ObjectCosmos {
  protected Vector3 coord;
  protected Vector3 V;
  protected Vector3 a;
  protected BigDecimal mass;
  protected BigDecimal radius;

  public ObjectCosmos() {
    this.coord = new Vector3();
    this.V = new Vector3();
    this.a = new Vector3();
    this.mass = BigDecimal.ZERO;
    this.radius = BigDecimal.ZERO;
  }

  public ObjectCosmos(Vector3 coord, Vector3 V, Vector3 a, BigDecimal mass, BigDecimal radius) {
    this.coord = coord;
    this.V = V;
    this.a = a;
    this.mass = mass;
    this.radius = radius;
  }

  public void setCoord(Vector3 coord) {
    this.coord = coord;
  }

  public void setVelocity(Vector3 V) {
    this.V = V;
  }

  public void setAcceleration(Vector3 a) {
    this.a = a;
  }

  public void setMass(BigDecimal mass) {
    this.mass = mass;
  }

  public void setRadius(BigDecimal radius) {
    this.radius = radius;
  }

  public void addCoord(Vector3 coord) {
    this.coord.add(coord);
  }

  public void addVelocity(Vector3 V) {
    this.V.add(V);
  }

  public void addAcceleration(Vector3 a) {
    this.a.add(a);
  }

  public void addMass(BigDecimal mass) {
    this.mass = this.mass.add(mass);
  }

  public void addRadius(BigDecimal radius) {
    this.radius = this.radius.add(radius);
  }

  public Vector3 getCoord() {
    return this.coord;
  }

  public Vector3 getVelocity() {
    return this.V;
  }

  public Vector3 getAcceleration() {
    return this.a;
  }

  public BigDecimal getMass() {
    return this.mass;
  }

  public BigDecimal getRadius() {
    return this.radius;
  }

  public Vector3 setAcceleration() {
    return this.a;
  }

  public BigDecimal setMass() {
    return this.mass;
  }

  public BigDecimal setRadius() {
    return this.radius;
  }
}
