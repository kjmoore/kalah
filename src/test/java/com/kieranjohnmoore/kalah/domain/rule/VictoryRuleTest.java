package com.kieranjohnmoore.kalah.domain.rule;

import com.kieranjohnmoore.kalah.domain.Game;
import com.kieranjohnmoore.kalah.domain.exception.InvalidMoveException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VictoryRuleTest {
  private VictoryRule rule;
  private Game game;

  @Before
  public void setup() {
    rule = new VictoryRule();
    game = new Game();
  }

  @Test
  public void player1EmptyVictory() {
    //Ensure one side of the board is empty
    game.getBoard().setPitCount(1,0);
    game.getBoard().setPitCount(2,0);
    game.getBoard().setPitCount(3,0);
    game.getBoard().setPitCount(4,0);
    game.getBoard().setPitCount(5,0);
    game.getBoard().setPitCount(6,0);
    game.getBoard().setPitCount(7,10);
    game.getBoard().setPitCount(8,1);
    game.getBoard().setPitCount(9,1);
    game.getBoard().setPitCount(10,1);
    game.getBoard().setPitCount(11,1);
    game.getBoard().setPitCount(12,1);
    game.getBoard().setPitCount(13,1);
    game.getBoard().setPitCount(14,10);

    rule.applyRule(game, 1);

    Assert.assertNotEquals("The game was not classed as a win", 0, game.getWinner());
  }

  @Test
  public void player2EmptyVictory() {
    //Ensure one side of the board is empty
    game.getBoard().setPitCount(1,1);
    game.getBoard().setPitCount(2,1);
    game.getBoard().setPitCount(3,1);
    game.getBoard().setPitCount(4,1);
    game.getBoard().setPitCount(5,1);
    game.getBoard().setPitCount(6,1);
    game.getBoard().setPitCount(7,10);
    game.getBoard().setPitCount(8,0);
    game.getBoard().setPitCount(9,0);
    game.getBoard().setPitCount(10,0);
    game.getBoard().setPitCount(11,0);
    game.getBoard().setPitCount(12,0);
    game.getBoard().setPitCount(13,0);
    game.getBoard().setPitCount(14,10);

    rule.applyRule(game, 1);

    Assert.assertNotEquals("The game was not classed as a win", 0, game.getWinner());
  }

  @Test
  public void checkPlayer1Count() {
    //Ensure one side of the board is empty
    game.getBoard().setPitCount(1,1);
    game.getBoard().setPitCount(2,1);
    game.getBoard().setPitCount(3,1);
    game.getBoard().setPitCount(4,1);
    game.getBoard().setPitCount(5,1);
    game.getBoard().setPitCount(6,1);
    game.getBoard().setPitCount(7,10);
    game.getBoard().setPitCount(8,0);
    game.getBoard().setPitCount(9,0);
    game.getBoard().setPitCount(10,0);
    game.getBoard().setPitCount(11,0);
    game.getBoard().setPitCount(12,0);
    game.getBoard().setPitCount(13,0);
    game.getBoard().setPitCount(14,10);

    rule.applyRule(game, 1);

    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(1));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(2));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(3));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(4));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(5));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(6));
    Assert.assertEquals("Incorrect number of stones in pot", 16, game.getBoard().getPitCount(7));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(8));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(9));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(10));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(11));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(12));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(13));
    Assert.assertEquals("Incorrect number of stones in pot", 10, game.getBoard().getPitCount(14));
  }

  @Test
  public void checkPlayer2Count() {
    //Ensure one side of the board is empty
    game.getBoard().setPitCount(1,0);
    game.getBoard().setPitCount(2,0);
    game.getBoard().setPitCount(3,0);
    game.getBoard().setPitCount(4,0);
    game.getBoard().setPitCount(5,0);
    game.getBoard().setPitCount(6,0);
    game.getBoard().setPitCount(7,10);
    game.getBoard().setPitCount(8,1);
    game.getBoard().setPitCount(9,1);
    game.getBoard().setPitCount(10,1);
    game.getBoard().setPitCount(11,1);
    game.getBoard().setPitCount(12,1);
    game.getBoard().setPitCount(13,1);
    game.getBoard().setPitCount(14,10);

    rule.applyRule(game, 1);

    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(1));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(2));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(3));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(4));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(5));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(6));
    Assert.assertEquals("Incorrect number of stones in pot", 10, game.getBoard().getPitCount(7));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(8));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(9));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(10));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(11));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(12));
    Assert.assertEquals("Incorrect number of stones in pot", 0, game.getBoard().getPitCount(13));
    Assert.assertEquals("Incorrect number of stones in pot", 16, game.getBoard().getPitCount(14));
  }

  @Test
  public void checkPlayer1Victory() {
    //Ensure one side of the board is empty
    game.getBoard().setPitCount(1,1);
    game.getBoard().setPitCount(2,1);
    game.getBoard().setPitCount(3,1);
    game.getBoard().setPitCount(4,1);
    game.getBoard().setPitCount(5,1);
    game.getBoard().setPitCount(6,1);
    game.getBoard().setPitCount(7,10);
    game.getBoard().setPitCount(8,0);
    game.getBoard().setPitCount(9,0);
    game.getBoard().setPitCount(10,0);
    game.getBoard().setPitCount(11,0);
    game.getBoard().setPitCount(12,0);
    game.getBoard().setPitCount(13,0);
    game.getBoard().setPitCount(14,10);

    rule.applyRule(game, 1);

    Assert.assertEquals("Player 1 did not win", 1, game.getWinner());
  }

  @Test
  public void checkPlayer1Victory2() {
    //Ensure one side of the board is empty
    game.getBoard().setPitCount(1,0);
    game.getBoard().setPitCount(2,0);
    game.getBoard().setPitCount(3,0);
    game.getBoard().setPitCount(4,0);
    game.getBoard().setPitCount(5,0);
    game.getBoard().setPitCount(6,0);
    game.getBoard().setPitCount(7,25);
    game.getBoard().setPitCount(8,0);
    game.getBoard().setPitCount(9,0);
    game.getBoard().setPitCount(10,0);
    game.getBoard().setPitCount(11,1);
    game.getBoard().setPitCount(12,1);
    game.getBoard().setPitCount(13,1);
    game.getBoard().setPitCount(14,10);

    rule.applyRule(game, 1);

    Assert.assertEquals("Player 1 did not win", 1, game.getWinner());
  }

  @Test
  public void checkPlayer2Victory() {
    //Ensure one side of the board is empty
    game.getBoard().setPitCount(1,0);
    game.getBoard().setPitCount(2,0);
    game.getBoard().setPitCount(3,0);
    game.getBoard().setPitCount(4,0);
    game.getBoard().setPitCount(5,0);
    game.getBoard().setPitCount(6,0);
    game.getBoard().setPitCount(7,10);
    game.getBoard().setPitCount(8,1);
    game.getBoard().setPitCount(9,1);
    game.getBoard().setPitCount(10,1);
    game.getBoard().setPitCount(11,1);
    game.getBoard().setPitCount(12,1);
    game.getBoard().setPitCount(13,1);
    game.getBoard().setPitCount(14,10);

    rule.applyRule(game, 1);

    Assert.assertEquals("Player 2 did not win", 2, game.getWinner());
  }

  @Test
  public void checkDraw() {
    //Ensure one side of the board is empty
    game.getBoard().setPitCount(1,0);
    game.getBoard().setPitCount(2,0);
    game.getBoard().setPitCount(3,0);
    game.getBoard().setPitCount(4,0);
    game.getBoard().setPitCount(5,0);
    game.getBoard().setPitCount(6,0);
    game.getBoard().setPitCount(7,16);
    game.getBoard().setPitCount(8,1);
    game.getBoard().setPitCount(9,1);
    game.getBoard().setPitCount(10,1);
    game.getBoard().setPitCount(11,1);
    game.getBoard().setPitCount(12,1);
    game.getBoard().setPitCount(13,1);
    game.getBoard().setPitCount(14,10);

    rule.applyRule(game, 1);

    Assert.assertEquals("It was not a draw", 3, game.getWinner());
  }
}
