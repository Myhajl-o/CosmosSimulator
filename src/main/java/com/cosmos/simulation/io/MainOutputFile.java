package com.cosmos.simulation.io;

import com.cosmos.simulation.model.ObjectCosmos;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MainOutputFile implements MainOutput {
  private MenuTerm menu;

  public MainOutputFile() {
  }

  /** Reuses the application's menu for file error messages. */
  public MainOutputFile(MenuTerm menu) {
    this.menu = menu;
  }

  /** Replaces the file with one semicolon-separated record per object. */
  @Override
  public void outputObj(List<ObjectCosmos> objects, String path) {
    try (BufferedWriter writer = Files.newBufferedWriter(Path.of(path), StandardCharsets.UTF_8)) {
      for (ObjectCosmos object : objects) {
        writer.write(String.join(";",
            object.getCoord().x.toString(),
            object.getCoord().y.toString(),
            object.getCoord().z.toString(),
            object.getVelocity().x.toString(),
            object.getVelocity().y.toString(),
            object.getVelocity().z.toString(),
            object.getAcceleration().x.toString(),
            object.getAcceleration().y.toString(),
            object.getAcceleration().z.toString(),
            object.getMass().toString(),
            object.getRadius().toString()));
        writer.newLine();
      }
    } catch (IOException e) {
      if (menu == null) {
        menu = new MenuTerm();
      }
      menu.showMsg("No Find the File");
    }
  }
}
