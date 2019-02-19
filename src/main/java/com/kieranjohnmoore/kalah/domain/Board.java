package com.kieranjohnmoore.kalah.domain;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

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

  public int getPitCount(int i) {
    return Integer.parseInt(state.get(i));
  }

  public void setLastStone(int localtion) {
    lastStone = localtion;
  }

  public void setPitCount(int location, int newValue) {
    state.put(location, Integer.toString(newValue));
  }

  @Override
  public String toString() {
    return new Gson().toJson(state);
  }

  public int getLastSeed() {
    return lastStone;
  }
}
