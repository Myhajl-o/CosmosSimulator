package com.cosmos.simulation.io;

import java.io.IOException;

/** A typed input/output channel. */
public interface InOut<T> {
  T input() throws IOException;

  void output(T value) throws IOException;
}
