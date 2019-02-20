package com.kieranjohnmoore.kalah.domain.rule;

import com.kieranjohnmoore.kalah.domain.Board;
import com.kieranjohnmoore.kalah.domain.Game;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

/**
 * This rule checks for the victory condition after a move
 */
@Order(4)
@Component
public class VictoryRule implements Rule {
  @Override
  public void applyRule(Game game, int move) {
    final Board board = game.getBoard();
    final boolean player1Empty =
        IntStream.range(1, 6).allMatch(i -> board.getPitCount(i) == 0);
    final boolean player2Empty =
        IntStream.range(8, 13).allMatch(i -> board.getPitCount(i) == 0);

    if (player1Empty || player2Empty) {
      int player1Score = IntStream.range(1,8).boxed().mapToInt(board::getPitCount).sum();
      int player2Score = IntStream.range(8,15).boxed().mapToInt(board::getPitCount).sum();

      if (player1Score == player2Score) {
        game.setWinner(3);
      } else if (player1Score > player2Score) {
        game.setWinner(1);
      } else {
        game.setWinner(2);
      }

      IntStream.range(1,14).forEach(i -> board.setPitCount(i, 0));
      board.setPitCount(7, player1Score);
      board.setPitCount(14, player2Score);
    }
  }
}
