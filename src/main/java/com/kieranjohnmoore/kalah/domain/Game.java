package com.kieranjohnmoore.kalah.domain;

/**
 * Representation of an ongoing game
 */
public class Game {
  private String player1Token;
  private String player2Token;
  private int playerTurn = 1;
  private final Board board = new Board();
  private int winner;

  /**
   * Retrieves the board representation for editing
   * @return the board
   */
  public Board getBoard() {
    return board;
  }

  /**
   * @return Player 1's token or null if not joined
   */
  public String getPlayer1Token() {
    return player1Token;
  }

  public void setPlayer1Token(String player1Token) {
    this.player1Token = player1Token;
  }

  /**
   * @return Player 2's token or null if not joined
   */
  public String getPlayer2Token() {
    return player2Token;
  }

  public void setPlayer2Token(String player2Token) {
    this.player2Token = player2Token;
  }

  /**
   * @return 1 if it is the first players turn, 2 if it is the second players turn
   */
  public int getPlayerTurn() {
    return playerTurn;
  }

  public void setPlayerTurn(int playerTurn) {
    this.playerTurn = playerTurn;
  }

  /**
   * @return 1 if player 1 has won, 2 if player 2 has won or 0 if the game is not over
   */
  public int getWinner() {
    return winner;
  }

  public void setWinner(int winner) {
    this.winner = winner;
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
