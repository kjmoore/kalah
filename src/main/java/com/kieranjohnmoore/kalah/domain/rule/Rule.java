package com.kieranjohnmoore.kalah.domain.rule;

import com.kieranjohnmoore.kalah.domain.Game;
import com.kieranjohnmoore.kalah.domain.exception.InvalidMoveException;

public interface Rule {
  /**
   * Applies a Kalah rule
   * @param game The board game
   * @param move The chosen pit
   * @throws InvalidMoveException if the choice is invalid, the board should be reset to it's
   * position before this move was attempted
   */
  void applyRule(Game game, int move) throws InvalidMoveException;
}
