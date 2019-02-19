package com.kieranjohnmoore.kalah.domain.rule;

import com.kieranjohnmoore.kalah.domain.Game;
import com.kieranjohnmoore.kalah.domain.exception.InvalidMoveException;

import org.junit.Before;
import org.junit.Test;

public class ValidMoveInOwnPitRuleTest {
  private ValidMoveInOwnPitRule rule;
  private Game game;

  @Before
  public void setup() {
    rule = new ValidMoveInOwnPitRule();
    game = new Game();
  }
  
  @Test
  public void allLegalMovesPlayer1() throws InvalidMoveException {
    game.setPlayerTurn(1);

    rule.applyRule(game, 1);
    rule.applyRule(game, 2);
    rule.applyRule(game, 3);
    rule.applyRule(game, 4);
    rule.applyRule(game, 5);
    rule.applyRule(game, 6);
  }

  @Test
  public void allLegalMovesPlayer2() throws InvalidMoveException {
    game.setPlayerTurn(2);

    rule.applyRule(game, 8);
    rule.applyRule(game, 9);
    rule.applyRule(game, 10);
    rule.applyRule(game, 11);
    rule.applyRule(game, 12);
    rule.applyRule(game, 13);
  }

  @Test(expected = InvalidMoveException.class)
  public void playedInHouse1() throws InvalidMoveException {
    rule.applyRule(game, 7);
  }

  @Test(expected = InvalidMoveException.class)
  public void playedInHouse2() throws InvalidMoveException {
    rule.applyRule(game, 14);
  }

  @Test(expected = InvalidMoveException.class)
  public void playedInOpponents1() throws InvalidMoveException {
    game.setPlayerTurn(2);
    rule.applyRule(game, 1);
  }

  @Test(expected = InvalidMoveException.class)
  public void playedInOpponents2() throws InvalidMoveException {
    game.setPlayerTurn(2);
    rule.applyRule(game, 2);
  }

  @Test(expected = InvalidMoveException.class)
  public void playedInOpponents3() throws InvalidMoveException {
    game.setPlayerTurn(2);
    rule.applyRule(game, 3);
  }

  @Test(expected = InvalidMoveException.class)
  public void playedInOpponents4() throws InvalidMoveException {
    game.setPlayerTurn(2);
    rule.applyRule(game, 4);
  }

  @Test(expected = InvalidMoveException.class)
  public void playedInOpponents5() throws InvalidMoveException {
    game.setPlayerTurn(2);
    rule.applyRule(game, 5);
  }

  @Test(expected = InvalidMoveException.class)
  public void playedInOpponents6() throws InvalidMoveException {
    game.setPlayerTurn(2);
    rule.applyRule(game, 6);
  }

  @Test(expected = InvalidMoveException.class)
  public void playedInOpponents8() throws InvalidMoveException {
    game.setPlayerTurn(1);
    rule.applyRule(game, 8);
  }

  @Test(expected = InvalidMoveException.class)
  public void playedInOpponents9() throws InvalidMoveException {
    game.setPlayerTurn(1);
    rule.applyRule(game, 9);
  }

  @Test(expected = InvalidMoveException.class)
  public void playedInOpponents10() throws InvalidMoveException {
    game.setPlayerTurn(1);
    rule.applyRule(game, 10);
  }

  @Test(expected = InvalidMoveException.class)
  public void playedInOpponents11() throws InvalidMoveException {
    game.setPlayerTurn(1);
    rule.applyRule(game, 11);
  }

  @Test(expected = InvalidMoveException.class)
  public void playedInOpponents12() throws InvalidMoveException {
    game.setPlayerTurn(1);
    rule.applyRule(game, 12);
  }

  @Test(expected = InvalidMoveException.class)
  public void playedInOpponents13() throws InvalidMoveException {
    game.setPlayerTurn(1);
    rule.applyRule(game, 13);
  }

  @Test(expected = InvalidMoveException.class)
  public void playedEmptyPit() throws InvalidMoveException {
    game.setPlayerTurn(1);
    game.getBoard().setPitCount(1, 0);

    rule.applyRule(game, 1);
  }
}
