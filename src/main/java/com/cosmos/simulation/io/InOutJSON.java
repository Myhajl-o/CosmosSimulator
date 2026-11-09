package com.cosmos.simulation.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.Strictness;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;

/** Reads and writes one typed object in a versioned UTF-8 JSON document. */
public final class InOutJSON<T> implements InOut<T> {
  private static final String FORMAT = "cosmos-simulation";
  private static final int VERSION = 1;
  private static final Set<String> DOCUMENT_FIELDS = Set.of("format", "version", "data");

  private final Path path;
  private final Class<T> objectType;
  private final Gson gson;

  public InOutJSON(Path path, Class<T> objectType) {
    this.path = Objects.requireNonNull(path);
    this.objectType = Objects.requireNonNull(objectType);
    if (objectType.isInterface() || Modifier.isAbstract(objectType.getModifiers())) {
      throw new IllegalArgumentException("A concrete object type is required");
    }
    gson = new GsonBuilder()
        .setStrictness(Strictness.STRICT)
        .setPrettyPrinting()
        .serializeNulls()
        .disableJdkUnsafe()
        .create();
  }

  public InOutJSON(String path, Class<T> objectType) {
    this(Path.of(path), objectType);
  }

  @Override
  public T input() throws IOException {
    return input(path);
  }

  /** Reads from an explicit path without changing the default path. */
  public T input(Path source) throws IOException {
    String json = Files.readString(Objects.requireNonNull(source), StandardCharsets.UTF_8);
    try {
      JsonElement parsed = gson.fromJson(json, JsonElement.class);
      if (parsed == null || !parsed.isJsonObject()) {
        throw new IOException("The document must be a JSON object");
      }
      JsonObject document = parsed.getAsJsonObject();
      if (!document.keySet().equals(DOCUMENT_FIELDS)) {
        throw new IOException("The document must contain exactly format, version and data");
      }
      JsonElement format = document.get("format");
      JsonElement version = document.get("version");
      if (!format.isJsonPrimitive() || !format.getAsJsonPrimitive().isString()
          || !FORMAT.equals(format.getAsString())) {
        throw new IOException("Unsupported document format");
      }
      if (!version.isJsonPrimitive() || !version.getAsJsonPrimitive().isNumber()
          || !Integer.toString(VERSION).equals(version.getAsString())) {
        throw new IOException("Unsupported document version");
      }
      if (!document.get("data").isJsonObject()) {
        throw new IOException("The data field must contain an object");
      }
      T value = gson.fromJson(document.get("data"), objectType);
      if (value == null) {
        throw new IOException("The data field must not be null");
      }
      return value;
    } catch (JsonParseException | IllegalStateException e) {
      throw new IOException("Invalid JSON document: " + source, e);
    }
  }

  @Override
  public void output(T value) throws IOException {
    output(path, value);
  }

  /** Replaces the target file. Its parent directory must already exist. */
  public void output(Path target, T value) throws IOException {
    Objects.requireNonNull(target);
    Objects.requireNonNull(value);
    JsonObject document = new JsonObject();
    document.addProperty("format", FORMAT);
    document.addProperty("version", VERSION);
    final String json;
    try {
      JsonElement data = gson.toJsonTree(objectType.cast(value), objectType);
      if (!data.isJsonObject()) {
        throw new IOException("Only object values can be written");
      }
      document.add("data", data);
      json = gson.toJson(document) + System.lineSeparator();
    } catch (JsonParseException | IllegalArgumentException e) {
      throw new IOException("Could not serialize object", e);
    }
    Files.writeString(target, json, StandardCharsets.UTF_8);
  }
}
