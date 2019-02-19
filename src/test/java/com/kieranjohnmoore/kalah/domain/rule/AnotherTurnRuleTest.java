package com.kieranjohnmoore.kalah.domain.rule;

import com.kieranjohnmoore.kalah.domain.Game;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnotherTurnRuleTest {
  private AnotherTurnRule rule;
  private Game game;

  @Before
  public void setup() {
    rule = new AnotherTurnRule();
    game = new Game();
  }

  @Test
  public void anotherGo() {
    game.setPlayerTurn(1);
    game.getBoard().setPitCount(7,1);
    game.getBoard().setLastStone(7);

    //The move doesn't matter here, it's the end pit that will be
    //checked
    rule.applyRule(game, 0);

    Assert.assertEquals("The player did not get another turn", 1,  game.getPlayerTurn());
  }
  @Test
  public void anotherGoPlayer2() {
    game.setPlayerTurn(2);
    game.getBoard().setPitCount(14,1);
    game.getBoard().setLastStone(14);

    //The move doesn't matter here, it's the end pit that will be
    //checked
    rule.applyRule(game, 0);

    Assert.assertEquals("The player did not get another turn", 2, game.getPlayerTurn());
  }

  @Test
  public void opponentsTurn() {
    game.setPlayerTurn(1);
    game.getBoard().setPitCount(8,1);
    game.getBoard().setLastStone(8);

    //The move doesn't matter here, it's the end pit that will be
    //checked
    rule.applyRule(game, 0);

    Assert.assertEquals("The player got an extra turn", 2, game.getPlayerTurn());
  }
}
