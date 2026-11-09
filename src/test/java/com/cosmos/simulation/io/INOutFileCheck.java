package com.cosmos.simulation.io;

import com.cosmos.simulation.model.ObjectCosmos;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Standalone integration check for the numeric file format. */
public final class INOutFileCheck {
  private static final class Body extends ObjectCosmos {
  }

  public static void main(String[] args) throws Exception {
    Path path = Files.createTempFile("cosmos-numeric-", ".txt");
    try {
      InOutFile file = new InOutFile(path, Body::new);
      String record = "20,50,120,12,0,0,0,-0.01234567890123456789,0,1000,10";
      Files.writeString(path, record + "\n");
      ObjectCosmos object = file.input();
      if (!(object instanceof Body) || !object.getCoord().x.toString().equals("20")
          || !object.getMass().toString().equals("1000")
          || !object.getRadius().toString().equals("10")) {
        throw new AssertionError("Incorrect object or field order");
      }
      file.output(object);
      if (!Files.readString(path).strip().equals(record)) {
        throw new AssertionError("Numeric values must survive a round trip exactly");
      }
      Files.writeString(path, " 20, 50,120,12,0,0,0,-0.01,0,1E+3,10 \n");
      file.output(file.input());
      for (String invalid : new String[] { "", "1,2", record + ",", record + "\n" + record,
          record.replace("1000", "invalid"), record.replace("1000", "") }) {
        Files.writeString(path, invalid);
        try {
          file.input();
          throw new AssertionError("Expected IOException: " + invalid);
        } catch (IOException expected) {
          // Expected invalid input.
        }
      }
      Files.delete(path);
      try {
        file.input();
        throw new AssertionError("Expected missing-file failure");
      } catch (IOException expected) {
        // Expected missing file.
      }
      System.out.println("INOutFile checks passed");
    } finally {
      Files.deleteIfExists(path);
    }
  }
}
