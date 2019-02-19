package com.kieranjohnmoore.kalah.domain.rule;

import com.kieranjohnmoore.kalah.domain.Game;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoveSeedRuleTest {
  private MoveSeedRule rule;
  private Game game;

  @Before
  public void setup() {
    rule = new MoveSeedRule();
    game = new Game();
  }

  @Test
  public void placeSeedPlayer1() {
    game.setPlayerTurn(1);

    rule.applyRule(game, 1);

    Assert.assertEquals("The stones were not removed from the selected pot", 0,
        game.getBoard().getPitCount(1));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(2));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(3));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(4));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(5));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(6));
    Assert.assertEquals("The stones were not added to the house", 1,
        game.getBoard().getPitCount(7));
    Assert.assertEquals("Additional stones were added", 6,
        game.getBoard().getPitCount(8));
    Assert.assertEquals("The last seed location was not set", 7,
        game.getBoard().getLastSeed());
  }

  @Test
  public void placeSeedWithWrapPlayer1() {
    game.setPlayerTurn(1);

    rule.applyRule(game, 5);

    Assert.assertEquals("The stones were not removed from the selected pot", 0,
        game.getBoard().getPitCount(5));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(6));
    Assert.assertEquals("The stones were not added to the house", 1,
        game.getBoard().getPitCount(7));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(8));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(9));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(10));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(11));
    Assert.assertEquals("Additional stones were added", 6,
        game.getBoard().getPitCount(12));
    Assert.assertEquals("The last seed location was not set", 11,
        game.getBoard().getLastSeed());
  }

  @Test
  public void placeSeedPlayer2() {
    game.setPlayerTurn(2);

    rule.applyRule(game, 8);

    Assert.assertEquals("The stones were not removed from the selected pot", 0,
        game.getBoard().getPitCount(8));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(9));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(10));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(11));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(12));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(13));
    Assert.assertEquals("The stones were not added to the house", 1,
        game.getBoard().getPitCount(14));
    Assert.assertEquals("Additional stones were added", 6,
        game.getBoard().getPitCount(1));
    Assert.assertEquals("The last seed location was not set", 14,
        game.getBoard().getLastSeed());
  }

  @Test
  public void placeSeedWithWrapPlayer2() {
    game.setPlayerTurn(2);

    rule.applyRule(game, 12);

    Assert.assertEquals("The stones were not removed from the selected pot", 0,
        game.getBoard().getPitCount(12));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(13));
    Assert.assertEquals("The stones were not added to the house", 1,
        game.getBoard().getPitCount(14));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(1));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(2));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(3));
    Assert.assertEquals("The stones were not added to the next pot", 7,
        game.getBoard().getPitCount(4));
    Assert.assertEquals("Additional stones were added", 6,
        game.getBoard().getPitCount(5));
    Assert.assertEquals("The last seed location was not set", 4,
        game.getBoard().getLastSeed());
  }

  @Test
  public void placeLotsOfSeeds() {
    game.setPlayerTurn(1);

    game.getBoard().setPitCount(1,1);
    game.getBoard().setPitCount(2,1);
    game.getBoard().setPitCount(3,1);
    game.getBoard().setPitCount(4,1);
    game.getBoard().setPitCount(5,1);
    //Put a lot of seeds in one of the bowls
    game.getBoard().setPitCount(6,20);
    game.getBoard().setPitCount(7,1);
    game.getBoard().setPitCount(8,1);
    game.getBoard().setPitCount(9,1);
    game.getBoard().setPitCount(10,1);
    game.getBoard().setPitCount(11,1);
    game.getBoard().setPitCount(12,1);
    game.getBoard().setPitCount(13,1);
    game.getBoard().setPitCount(14,1);

    rule.applyRule(game, 6);

    Assert.assertEquals("Incorrect number of stones in pot", 2, game.getBoard().getPitCount(1));
    Assert.assertEquals("Incorrect number of stones in pot", 2, game.getBoard().getPitCount(2));
    Assert.assertEquals("Incorrect number of stones in pot", 2, game.getBoard().getPitCount(3));
    Assert.assertEquals("Incorrect number of stones in pot", 2, game.getBoard().getPitCount(4));
    Assert.assertEquals("Incorrect number of stones in pot", 2, game.getBoard().getPitCount(5));
    Assert.assertEquals("Incorrect number of stones in pot", 1, game.getBoard().getPitCount(6));
    Assert.assertEquals("Incorrect number of stones in pot", 3, game.getBoard().getPitCount(7));
    Assert.assertEquals("Incorrect number of stones in pot", 3, game.getBoard().getPitCount(8));
    Assert.assertEquals("Incorrect number of stones in pot", 3, game.getBoard().getPitCount(9));
    Assert.assertEquals("Incorrect number of stones in pot", 3, game.getBoard().getPitCount(10));
    Assert.assertEquals("Incorrect number of stones in pot", 3, game.getBoard().getPitCount(11));
    Assert.assertEquals("Incorrect number of stones in pot", 3, game.getBoard().getPitCount(12));
    Assert.assertEquals("Incorrect number of stones in pot", 3, game.getBoard().getPitCount(13));
    Assert.assertEquals("Incorrect number of stones in pot", 1, game.getBoard().getPitCount(14));
    Assert.assertEquals("The last seed location was not set", 13, game.getBoard().getLastSeed());
  }
}
