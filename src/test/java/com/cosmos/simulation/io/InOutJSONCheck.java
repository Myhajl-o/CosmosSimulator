package com.cosmos.simulation.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Standalone integration check; run main with the production dependencies. */
public final class InOutJSONCheck {
  public record Vector(String x, String y, String z) {}
  public record Payload(String name, Vector coord) {}

  public static void main(String[] args) throws Exception {
    Path directory = Files.createTempDirectory("cosmos-file-check-");
    Path path = directory.resolve("object.json");
    try {
      InOutJSON<Payload> file = new InOutJSON<>(path, Payload.class);
      Payload expected = new Payload("Зоря \"A\"\n☀", new Vector("1.234567890123456789", "-2", "0"));
      file.output(expected);
      require(expected.equals(file.input()), "Nested Unicode object must survive a round trip");
      file.output(new Payload("replacement", new Vector("0", "0", "0")));
      require(file.input().name().equals("replacement"), "Output must replace the previous document");
      String valid = Files.readString(path);
      for (String invalid : new String[] {
          "", "null", "[]", "{}", valid + " {}",
          valid.replace("cosmos-simulation", "other"),
          valid.replace("\"version\": 1", "\"version\": 2"),
          valid.replace("\"version\": 1", "\"version\": \"1\""),
          "{\"format\":\"cosmos-simulation\",\"version\":1,\"data\":null}",
          "{\"format\":\"cosmos-simulation\",\"version\":1,\"data\":{},\"extra\":0}"
      }) {
        Files.writeString(path, invalid);
        expectIOException(file::input);
      }
      Files.delete(path);
      expectIOException(file::input);
      expectIOException(() -> file.output(directory.resolve("missing/object.json"), expected));
      System.out.println("InOutJSON checks passed");
    } finally {
      Files.deleteIfExists(path);
      Files.delete(directory);
    }
  }

  private static void expectIOException(IoAction action) throws Exception {
    try {
      action.run();
      throw new AssertionError("Expected IOException");
    } catch (IOException expected) {
      // Expected failure.
    }
  }

  private static void require(boolean condition, String message) {
    if (!condition) {
      throw new AssertionError(message);
    }
  }

  private interface IoAction {
    void run() throws IOException;
  }
}
