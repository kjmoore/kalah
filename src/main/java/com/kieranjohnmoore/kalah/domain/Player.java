package com.kieranjohnmoore.kalah.domain;

import com.google.gson.Gson;

/**
 * Representation of a player
 */
public class Player {
  private final String token;
  private final int playerNumber;

  public Player(String token, int playerNumber) {
    this.token = token;
    this.playerNumber = playerNumber;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }

  /**
   * The player's turn number
   * @return 1 or 2 depending if they go first or second
   */
  public int getPlayerNumber() {
    return playerNumber;
  }

  /**
   * The player's secret token to allow them to make moves
   * @return the token
   */
  public String getToken() {
    return token;
  }
}
