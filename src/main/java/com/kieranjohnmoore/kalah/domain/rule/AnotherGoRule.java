package com.kieranjohnmoore.kalah.domain.rule;

import com.kieranjohnmoore.kalah.domain.Game;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(3)
@Component
public class AnotherGoRule implements Rule {
  @Override
  public void applyRule(Game game, int move){
    if (game.getPlayerTurn() == 1 && game.getBoard().getLastStone() != 7) {
      game.setPlayerTurn(2);
    } else if (game.getPlayerTurn() == 2 && game.getBoard().getLastStone() != 14) {
      game.setPlayerTurn(1);
    }
  }
}
