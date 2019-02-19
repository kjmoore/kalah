package com.kieranjohnmoore.kalah.domain.rule;

import com.kieranjohnmoore.kalah.domain.Game;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmptyPitCaptureRuleTests {
  private EmptyPitCaptureRule rule;
  private Game game;

  @Before
  public void setup() {
    rule = new EmptyPitCaptureRule();
    game = new Game();
  }

  @Test
  public void captureStones() {
    game.setPlayerTurn(1);
    //create en empty pit on this player's side
    game.getBoard().setPitCount(1,0);
    //create a full pit on the opponents side
    game.getBoard().setPitCount(13, 6);
    //place the last stone into this empty pit
    game.getBoard().setPitCount(1, 1);
    game.getBoard().setLastStone(1);

    //The move doesn't matter here, it's the end pit that will be checked
    rule.applyRule(game, 0);

    //Check the stones have been captured
    Assert.assertEquals("Players stones were not captured", 0, game.getBoard().getPitCount(1));
    Assert.assertEquals("Opponents stones were not captured", 0, game.getBoard().getPitCount(13));
    //Check the score has been added to the player's house
    Assert.assertEquals("Score was not updated", 7, game.getBoard().getPitCount(7));
  }

  @Test
  public void captureStonesPlayer2() {
    game.setPlayerTurn(2);
    //create en empty pit on this player's side
    game.getBoard().setPitCount(1,0);
    //create a full pit on the opponents side
    game.getBoard().setPitCount(1, 6);
    //place the last stone into this empty pit
    game.getBoard().setPitCount(13, 1);
    game.getBoard().setLastStone(13);

    //The move doesn't matter here, it's the end pit that will be checked
    rule.applyRule(game, 0);

    //Check the stones have been captured
    Assert.assertEquals("Players stones were not captured", 0, game.getBoard().getPitCount(13));
    Assert.assertEquals("Opponents stones were not captured",0,  game.getBoard().getPitCount(1));
    //Check the score has been added to the player's house
    Assert.assertEquals("Score was not updated", 7, game.getBoard().getPitCount(14));
  }

  @Test
  public void notEmpty() {
    game.setPlayerTurn(1);
    //create a full pit on this player's side
    game.getBoard().setPitCount(1,1);
    //create a full pit on the opponents side
    game.getBoard().setPitCount(13, 6);
    //place the last stone into this empty pit
    game.getBoard().setPitCount(1, 2);
    game.getBoard().setLastStone(1);

    //The move doesn't matter here, it's the end pit that will be checked
    rule.applyRule(game, 0);

    //Check the stones have not been captured
    Assert.assertEquals("Stones were captured without pit being empty",
        6, game.getBoard().getPitCount(13));
    //Check the score has not been added to the player's house
    Assert.assertEquals("Score was changed when nothing captured",
        0, game.getBoard().getPitCount(7));
  }

  @Test
  public void endInHouse() {
    game.setPlayerTurn(1);
    //fill the board
    game.getBoard().setPitCount(1,1);
    game.getBoard().setPitCount(2,1);
    game.getBoard().setPitCount(3,1);
    game.getBoard().setPitCount(4,1);
    game.getBoard().setPitCount(5,1);
    game.getBoard().setPitCount(6,1);
    game.getBoard().setPitCount(7,0);
    game.getBoard().setPitCount(8,1);
    game.getBoard().setPitCount(9,1);
    game.getBoard().setPitCount(10,1);
    game.getBoard().setPitCount(11,1);
    game.getBoard().setPitCount(12,1);
    game.getBoard().setPitCount(13,1);
    game.getBoard().setPitCount(14,0);
    //place the last stone into the house
    game.getBoard().setPitCount(7, 1);
    game.getBoard().setLastStone(7);

    //The move doesn't matter here, it's the end pit that will be checked
    rule.applyRule(game, 0);

    //Check nothing has been captured
    Assert.assertEquals("A seed was incorrectly captured", 1, game.getBoard().getPitCount(1));
    Assert.assertEquals("A seed was incorrectly captured", 1, game.getBoard().getPitCount(2));
    Assert.assertEquals("A seed was incorrectly captured", 1, game.getBoard().getPitCount(3));
    Assert.assertEquals("A seed was incorrectly captured", 1, game.getBoard().getPitCount(4));
    Assert.assertEquals("A seed was incorrectly captured", 1, game.getBoard().getPitCount(5));
    Assert.assertEquals("A seed was incorrectly captured", 1, game.getBoard().getPitCount(6));
    Assert.assertEquals("A seed was incorrectly captured", 1, game.getBoard().getPitCount(7));
    Assert.assertEquals("A seed was incorrectly captured", 1, game.getBoard().getPitCount(8));
    Assert.assertEquals("A seed was incorrectly captured", 1, game.getBoard().getPitCount(9));
    Assert.assertEquals("A seed was incorrectly captured", 1, game.getBoard().getPitCount(10));
    Assert.assertEquals("A seed was incorrectly captured", 1, game.getBoard().getPitCount(11));
    Assert.assertEquals("A seed was incorrectly captured", 1, game.getBoard().getPitCount(12));
    Assert.assertEquals("A seed was incorrectly captured", 1, game.getBoard().getPitCount(13));
    Assert.assertEquals("Score was changed incorrectly", 0, game.getBoard().getPitCount(14));
  }
}
