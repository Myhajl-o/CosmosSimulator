package com.cosmos.simulation.io;

import java.io.IOException;
import java.util.List;

public interface MainInput {

  List<String> input() throws IOException;
}
