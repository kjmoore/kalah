package com.kieranjohnmoore.kalah.domain;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class Player {
  @Expose
  private final String token;
  @Expose
  private final int playerNumber;

  public Player(String token, int playerNumber) {
    this.token = token;
    this.playerNumber = playerNumber;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
