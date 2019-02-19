package com.kieranjohnmoore.kalah.domain.rule;

import com.kieranjohnmoore.kalah.domain.Game;
import com.kieranjohnmoore.kalah.domain.exception.InvalidMoveException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * This rule checks that the player has played a valid move from one of their own pots
 */
@Order(0)
@Component
public class ValidMoveInOwnPitRule implements Rule {
  @Override
  public void applyRule(Game game, int move) throws InvalidMoveException {
    if (move > 14) {
      throw new InvalidMoveException("Seeds cannot be played outside of the game board");
    }

    if (move == 7 || move == 14) {
      throw new InvalidMoveException("Seeds cannot be played inside a house");
    }

    if (game.getBoard().getPitCount(move) == 0) {
      throw new InvalidMoveException("There are no seeds to pick up in this pit");
    }

    if ((game.getPlayerTurn() == 1 && (move > 7)) ||
        (game.getPlayerTurn() == 2 && (move < 7))) {
      throw new InvalidMoveException("Seeds must be played in your own pits");
    }
  }
}
