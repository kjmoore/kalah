package com.kieranjohnmoore.kalah.domain.rule;

import com.kieranjohnmoore.kalah.domain.Board;
import com.kieranjohnmoore.kalah.domain.Game;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class EmptyPitCaptureRule implements Rule {
  @Override
  public void applyRule(Game game, int move) {
    final Board board = game.getBoard();
    final int lastStone = board.getLastSeed();

    if ((game.getPlayerTurn() == 1 && lastStone <= 6) ||
        (game.getPlayerTurn() == 2 && lastStone >= 8)) {
      // The stone landed on their side
      if (board.getPitCount(lastStone) == 1) {
        //And the pit was previously empty
        int opposite = getOpositePit(lastStone);
        //Calculate score, will always be the opposite plus the one seed that was played
        int score = board.getPitCount(opposite) + 1;
        board.setPitCount(lastStone, 0);
        board.setPitCount(opposite, 0);

        if (game.getPlayerTurn() == 1) {
          score += board.getPitCount(7);
          board.setPitCount(7, score);
        } else {
          score += board.getPitCount(14);
          board.setPitCount(14, score);
        }
      }
    }

  }

  private int getOpositePit(int pit) {
    return 14 - pit;
  }
}
