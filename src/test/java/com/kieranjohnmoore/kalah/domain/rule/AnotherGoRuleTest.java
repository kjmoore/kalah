package com.kieranjohnmoore.kalah.domain.rule;

import com.kieranjohnmoore.kalah.domain.Game;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnotherGoRuleTest {
  private AnotherGoRule rule;
  private Game game;

  @Before
  public void setup() {
    rule = new AnotherGoRule();
    game = new Game();
  }

  @Test
  public void anotherGo() {
    game.setPlayerTurn(1);
    game.getBoard().setPitCount(7,1);

    //The move doesn't matter here, it's the end pit that will be
    //checked
    rule.applyRule(game, 0);

    Assert.assertEquals("The player did not get another turn", game.getPlayerTurn(), 1);
  }

  @Test
  public void opponentsTurn() {
    game.setPlayerTurn(1);
    game.getBoard().setPitCount(8,1);

    //The move doesn't matter here, it's the end pit that will be
    //checked
    rule.applyRule(game, 0);

    Assert.assertEquals("The player got an extra turn", game.getPlayerTurn(), 2);
  }
}
