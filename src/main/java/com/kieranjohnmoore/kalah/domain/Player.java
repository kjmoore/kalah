package com.kieranjohnmoore.kalah.domain;

public class Player {
  private final String token;
  private final int playerNumber;

  public Player(String token, int playerNumber) {
    this.token = token;
    this.playerNumber = playerNumber;
  }

  @Override
  public String toString() {
    return "Player{" +
        "token='" + token + '\'' +
        ", playerNumber=" + playerNumber +
        '}';
  }
}
