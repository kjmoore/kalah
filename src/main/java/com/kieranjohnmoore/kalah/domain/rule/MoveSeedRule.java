package com.kieranjohnmoore.kalah.domain.rule;

import com.kieranjohnmoore.kalah.domain.Board;
import com.kieranjohnmoore.kalah.domain.Game;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * This rule handles the movement of seeds around the board, assuming the move is valid - this
 * will always happen
 */
@Order(1)
@Component
public class MoveSeedRule implements Rule {
  @Override
  public void applyRule(Game game, int move) {
    final Board board = game.getBoard();
    final int seeds = board.getPitCount(move);
    //Take all the seeds
    board.setPitCount(move, 0);

    int nextLocation = move;
    for (int i = 0; i < seeds; i++) {
      nextLocation++;

      if (game.getPlayerTurn() == 1 && nextLocation == 14) {
        //skip opponents house
        nextLocation = 1;
      }
      if (game.getPlayerTurn() == 2 && nextLocation == 7) {
        //skip opponents house
        nextLocation = 8;
      }
      if (nextLocation == 15) {
        //roll over back to the start
        nextLocation = 1;
      }

      addSeed(board, nextLocation);
    }
  }

  private void addSeed(Board board, int location) {
    int oldCount = board.getPitCount(location);
    oldCount++;
    board.setPitCount(location, oldCount);
    board.setLastStone(location);
  }
}
