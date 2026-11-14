package com.cosmos.simulation.io;

import java.util.Scanner;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

public class MainInputTerm implements MainInput {

  private List<String> msg;

  public MainInputTerm(List<String> msg) {
    this.msg = msg;
  }

  public List<String> input()
      throws IOException {
    List<String> param = new ArrayList<String>();
    Scanner scanf = new Scanner(System.in);

    for (String temp : msg) {
      System.out.print(temp);
      temp = scanf.nextLine();

      if (temp == "q" || temp == "quit") {
        scanf.close();
        return null;
      }
      param.add(temp);
    }
    scanf.close();
    return param;
  }

}
