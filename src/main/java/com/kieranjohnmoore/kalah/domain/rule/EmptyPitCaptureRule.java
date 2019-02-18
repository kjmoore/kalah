package com.kieranjohnmoore.kalah.domain.rule;

import com.kieranjohnmoore.kalah.domain.Game;
import com.kieranjohnmoore.kalah.domain.exception.InvalidMoveException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class EmptyPitCaptureRule implements Rule {
  @Override
  public void applyRule(Game game, int move) throws InvalidMoveException {

  }
}
