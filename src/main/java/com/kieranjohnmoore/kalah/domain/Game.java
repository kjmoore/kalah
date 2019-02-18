package com.kieranjohnmoore.kalah.domain;

public class Game {
  private String player1Token;
  private String player2Token;
  private int playerTurn = 1;
  private final Board board = new Board();

  public String getGameState() {
    return board.toString();
  }

  public Board getBoard() {
    return board;
  }

  public String getPlayer1Token() {
    return player1Token;
  }

  public void setPlayer1Token(String player1Token) {
    this.player1Token = player1Token;
  }

  public String getPlayer2Token() {
    return player2Token;
  }

  public void setPlayer2Token(String player2Token) {
    this.player2Token = player2Token;
  }

  public int getPlayerTurn() {
    return playerTurn;
  }

  public void setPlayerTurn(int playerTurn) {
    this.playerTurn = playerTurn;
  }

  @Override
  public String toString() {
    return "Game{" +
        "player1Token='" + player1Token + '\'' +
        ", player2Token='" + player2Token + '\'' +
        ", playerTurn=" + playerTurn +
        ", board=" + board +
        '}';
  }
}
