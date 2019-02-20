package com.kieranjohnmoore.kalah.model;

import com.kieranjohnmoore.kalah.domain.Game;

import java.util.Map;

public class GameResponse extends BaseResponse {
  private final Map<Integer, String> status;
  private final int playerTurn;
  private final int winner;

  public GameResponse(String id, Game game) {
    super(id);
    status = game.getBoard().getStatus();
    playerTurn = game.getPlayerTurn();
    winner = game.getWinner();
  }

  @SuppressWarnings("unused") //Used by Spring
  public Map<Integer, String> getStatus() {
    return status;
  }

  @SuppressWarnings("unused") //Used by Spring
  public int getPlayerTurn() {
    return playerTurn;
  }

  @SuppressWarnings("unused") //Used by Spring
  public int getWinner() {
    return winner;
  }
}
