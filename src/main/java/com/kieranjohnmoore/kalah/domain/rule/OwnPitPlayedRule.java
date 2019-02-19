package com.kieranjohnmoore.kalah.domain.rule;

import com.kieranjohnmoore.kalah.domain.Game;
import com.kieranjohnmoore.kalah.domain.exception.InvalidMoveException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(0)
@Component
public class OwnPitPlayedRule implements Rule {
  @Override
  public void applyRule(Game game, int move) throws InvalidMoveException {
    if (move > 14) {
      throw new InvalidMoveException("Seeds cannot be played outside of the game board");
    }

    if (move == 7 || move == 14) {
      throw new InvalidMoveException("Seeds cannot be played inside a house");
    }

    if ((game.getPlayerTurn() == 1 && (move > 7)) ||
        (game.getPlayerTurn() == 2 && (move < 7))) {
      throw new InvalidMoveException("Seeds must be played in your own pits");
    }
  }
}
