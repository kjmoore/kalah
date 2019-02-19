package com.kieranjohnmoore.kalah.domain.rule;

import com.kieranjohnmoore.kalah.domain.Game;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * This rule determines when a player gets another turn based on the
 * location of the final seed placed
 */
@Order(3)
@Component
public class AnotherTurnRule implements Rule {
  @Override
  public void applyRule(Game game, int move){
    if (game.getPlayerTurn() == 1 && game.getBoard().getLastSeed() != 7) {
      game.setPlayerTurn(2);
    } else if (game.getPlayerTurn() == 2 && game.getBoard().getLastSeed() != 14) {
      game.setPlayerTurn(1);
    }
  }
}
