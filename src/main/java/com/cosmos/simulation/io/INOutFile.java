package com.cosmos.simulation.io;

import com.cosmos.simulation.math.Vector3;
import com.cosmos.simulation.model.ObjectCosmos;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Stores one ObjectCosmos as eleven comma-separated decimal values. */
public final class INOutFile implements InOut<ObjectCosmos> {
  private final Path path;
  private final Supplier<? extends ObjectCosmos> objectFactory;

  /** The factory supplies the concrete object type, which is not stored in the file. */
  public INOutFile(Path path, Supplier<? extends ObjectCosmos> objectFactory) {
    this.path = Objects.requireNonNull(path);
    this.objectFactory = Objects.requireNonNull(objectFactory);
  }

  public INOutFile(String path, Supplier<? extends ObjectCosmos> objectFactory) {
    this(Path.of(path), objectFactory);
  }

  @Override
  public ObjectCosmos input() throws IOException {
    return input(path);
  }

  public ObjectCosmos input(Path source) throws IOException {
    String text = Files.readString(Objects.requireNonNull(source), StandardCharsets.UTF_8).strip();
    if (text.contains("\n") || text.contains("\r")) {
      throw new IOException("Expected one object on a single line");
    }
    String[] fields = text.split(",", -1);
    if (fields.length != 11) {
      throw new IOException("Expected 11 fields: x,y,z,vx,vy,vz,ax,ay,az,mass,radius");
    }
    BigDecimal[] values = new BigDecimal[11];
    for (int i = 0; i < fields.length; i++) {
      try {
        values[i] = new BigDecimal(fields[i].strip());
      } catch (NumberFormatException e) {
        throw new IOException("Invalid decimal value at field " + (i + 1), e);
      }
    }
    ObjectCosmos object = Objects.requireNonNull(objectFactory.get(), "The object factory returned null");
    object.setCoord(new Vector3(values[0], values[1], values[2]));
    object.setVelocity(new Vector3(values[3], values[4], values[5]));
    object.setAcceleration(new Vector3(values[6], values[7], values[8]));
    object.setMass(values[9]);
    object.setRadius(values[10]);
    return object;
  }

  @Override
  public void output(ObjectCosmos object) throws IOException {
    output(path, object);
  }

  /** Replaces the file with one numeric record, without a header or type name. */
  public void output(Path target, ObjectCosmos object) throws IOException {
    Objects.requireNonNull(target);
    Objects.requireNonNull(object);
    final String text;
    try {
      Vector3 coord = object.getCoord();
      Vector3 velocity = object.getVelocity();
      Vector3 acceleration = object.getAcceleration();
      text = Stream.of(coord.x, coord.y, coord.z,
              velocity.x, velocity.y, velocity.z,
              acceleration.x, acceleration.y, acceleration.z,
              object.getMass(), object.getRadius())
          .map(BigDecimal::toString)
          .collect(Collectors.joining(","));
    } catch (NullPointerException e) {
      throw new IOException("All vectors and numeric fields must have values", e);
    }
    Files.writeString(target, text + System.lineSeparator(), StandardCharsets.UTF_8);
  }
}
