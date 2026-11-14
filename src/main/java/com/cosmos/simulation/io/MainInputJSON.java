package com.cosmos.simulation.io;

import java.io.BufferedReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MainInputJSON implements MainInput {
  private String path;

  public MainInputJSON(String path) {
    this.path = path;
  }

  private void addVector(List<String> param, Object value) throws IOException {
    if (!(value instanceof JSONArray vector) || vector.size() != 3) {
      throw new IOException("Expected a vector with exactly three components");
    }
    for (Object component : vector) {
      param.add(readValue(component));
    }
  }

  private String readValue(Object value) throws IOException {
    if (!(value instanceof String) && !(value instanceof Number)) {
      throw new IOException("Expected a number or a numeric string");
    }
    return value.toString().strip();
  }

  public List<String> input()
      throws IOException {
    try (BufferedReader reader = Files.newBufferedReader(Path.of(path))) {
      Object parsed = new JSONParser().parse(reader);
      if (!(parsed instanceof JSONArray jsonObjects)) {
        return null;
      }
      List<String> param = new ArrayList<>();
      for (Object jsonObject : jsonObjects) {
        if (!(jsonObject instanceof JSONObject objCosmos)) {
          return null;
        }
        addVector(param, objCosmos.get("Coordinats"));
        addVector(param, objCosmos.get("Velocity"));
        addVector(param, objCosmos.get("Acceleration"));
        param.add(readValue(objCosmos.get("mass")));
        param.add(readValue(objCosmos.get("radius")));
      }
      return param;
    } catch (IOException | ParseException e) {
      throw new IOException(e);
    }
  }
}
