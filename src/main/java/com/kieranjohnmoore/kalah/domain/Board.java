package com.kieranjohnmoore.kalah.domain;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Representation of the the number of seeds in each pit on a board
 */
public class Board {
  private static final String STONES_PER_PIT = "6";

  private final Map<Integer, String> state;
  private int lastStone = 0;

  Board() {
    state = new HashMap<>();
    state.put(1, STONES_PER_PIT);
    state.put(2, STONES_PER_PIT);
    state.put(3, STONES_PER_PIT);
    state.put(4, STONES_PER_PIT);
    state.put(5, STONES_PER_PIT);
    state.put(6, STONES_PER_PIT);
    state.put(7, "0"); // Player 1's House
    state.put(8, STONES_PER_PIT);
    state.put(9, STONES_PER_PIT);
    state.put(10, STONES_PER_PIT);
    state.put(11, STONES_PER_PIT);
    state.put(12, STONES_PER_PIT);
    state.put(13, STONES_PER_PIT);
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

  @Override
  public String toString() {
    return new Gson().toJson(state);
  }
}
