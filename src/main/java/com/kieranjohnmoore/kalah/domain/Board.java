package com.kieranjohnmoore.kalah.domain;

import com.google.gson.Gson;

import org.hibernate.validator.internal.util.stereotypes.Immutable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Representation of the the number of seeds in each pit on a board
 */
public class Board {
  private static final String STONES_PER_PIT = "6";

  private final Map<Integer, String> state;
  private int lastStone = 0;

  Board() {
    state = new HashMap<>();
    IntStream.range(1, 15).forEach(i -> state.put(i, STONES_PER_PIT));
    state.put(7, "0"); // Player 1's House
    state.put(14, "0"); //Player 2's House
  }

  /**
   * @param location the location to check
   * @return the number of seeds in the pit specified by {@code location}
   */
  public int getPitCount(int location) {
    return Integer.parseInt(state.get(location));
  }

  /**
   * Updates the last pit that was placed into
   * @param location the last pit location
   */
  public void setLastStone(int location) {
    lastStone = location;
  }

  /**
   * Updates a pit to a new seed count
   * @param location the pit to update
   * @param newValue the new number of seeds
   */
  public void setPitCount(int location, int newValue) {
    state.put(location, Integer.toString(newValue));
  }

  /**
   * @return the last location of the last seed that was placed
   */
  public int getLastSeed() {
    return lastStone;
  }

  public Map<Integer, String> getStatus() {
    return Collections.unmodifiableMap(state);
  }

  @Override
  public String toString() {
    return new Gson().toJson(state);
  }
}
