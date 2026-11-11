package com.cosmos.simulation;

import java.util.Arrays;
import java.util.List;
import com.cosmos.simulation.io.MenuTerm;

public class TestMain {
  public static void main(String[] argc) {
    MenuTerm term = new MenuTerm();
    term.clear();
    List<String> argv = Arrays.asList("OBj", "Time", "Process");
    int ind = term.choiseOption(argv);
    term.clear();
    term.showMsg("\n" + argv.get(ind) + "\n");
  }
}
