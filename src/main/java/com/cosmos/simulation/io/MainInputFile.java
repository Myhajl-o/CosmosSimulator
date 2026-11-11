package com.cosmos.simulation.io;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.nio.file.Files;

public class MainInputFile implements MainInput {

  private String path;

  public void setPath(String path) {
    this.path = path;
  }

  public List<String> input(List<String> msg)
      throws IOException {
    if (path == null) {
      throw new IOException("Not found file to path");
    }
    List<String> param = new ArrayList<>();

    try (BufferedReader reader = Files.newBufferedReader(Path.of(this.path))) {

      String line = reader.readLine();

      if (line == null) {
        throw new IOException("Expected a line, but reached end of file");
      }

      try (Scanner scanner = new Scanner(line)) {
        scanner.useDelimiter(";");

        while (scanner.hasNext()) {
          param.add(scanner.next());
        }
      }
    }
    return param;
  }
}
