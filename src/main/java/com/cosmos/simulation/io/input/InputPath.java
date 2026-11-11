package com.cosmos.simulation.io.input;

import com.cosmos.simulation.io.MenuTerm;

public class InputPath {

  public static String input(MenuTerm menu) {

    if (menu == null) {
      menu = new MenuTerm();
    }

    String msg = "Enter the path: ";
    String path;
    menu.showMsg(msg);
    path = menu.getInput();
    return path;
  }
}
