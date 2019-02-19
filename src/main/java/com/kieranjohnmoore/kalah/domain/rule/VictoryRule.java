package com.kieranjohnmoore.kalah.domain.rule;

import com.kieranjohnmoore.kalah.domain.Game;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * This rule checks for the victory condition after a move
 */
@Order(4)
@Component
public class VictoryRule implements Rule {
  @Override
  public void applyRule(Game game, int move) {

  }
}
