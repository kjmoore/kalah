package com.kieranjohnmoore.kalah.service;

import com.kieranjohnmoore.kalah.domain.Game;
import com.kieranjohnmoore.kalah.domain.Player;
import com.kieranjohnmoore.kalah.domain.exception.GameDoesNotExistException;
import com.kieranjohnmoore.kalah.domain.exception.InvalidMoveException;
import com.kieranjohnmoore.kalah.domain.exception.UnableToJoinGameException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceTest {
  @Autowired
  GameService gameService;

  @Test
  public void createGame() {
    final String game = gameService.createGame();
    Assert.assertNotNull("A game was not created", game);
    Assert.assertFalse("A game was not created", game.isBlank());
  }

  @Test
  public void joinGame() throws GameDoesNotExistException, UnableToJoinGameException {
    final String gameId = gameService.createGame();
    final Player player1 = gameService.joinGame(gameId);
    final Player player2 = gameService.joinGame(gameId);

    Assert.assertNotNull("Player 1 was not created", player1);
    Assert.assertNotNull("Player 2 was not created", player2);

    Assert.assertEquals("Player 1 was not first", player1.getPlayerNumber(), 1);
    Assert.assertEquals("Player 2 was not second", player2.getPlayerNumber(), 2);

    Assert.assertNotNull("Player 1 did not have a token", player1.getToken());
    Assert.assertFalse("Player 1 did not have a token", player1.getToken().isBlank());
    Assert.assertNotNull("Player 2 did not have a token", player2.getToken());
    Assert.assertFalse("Player 2 did not have a token", player2.getToken().isBlank());
  }

  @Test
  public void makeMove() throws InvalidMoveException, GameDoesNotExistException, UnableToJoinGameException {
    final String gameId = gameService.createGame();
    final Player player1 = gameService.joinGame(gameId);
    final Player player2 = gameService.joinGame(gameId);

    final Game resultingGame = gameService.makeMove(gameId, player1.getToken(), 2);
    final Game resultingGame2 = gameService.makeMove(gameId, player2.getToken(), 11);

    Assert.assertNotNull("No resulting game returned for move 1", resultingGame);
    Assert.assertNotNull("No resulting game returned for move 2", resultingGame2);
  }

  @Test(expected = GameDoesNotExistException.class)
  public void joinInvalidGame() throws GameDoesNotExistException, UnableToJoinGameException {
    gameService.joinGame("1234");
  }

  @Test(expected = UnableToJoinGameException.class)
  public void joinFullGame() throws GameDoesNotExistException, UnableToJoinGameException {
    final String gameId = gameService.createGame();
    gameService.joinGame(gameId);
    gameService.joinGame(gameId);
    gameService.joinGame(gameId);
  }

  @Test(expected = InvalidMoveException.class)
  public void makeMoveWithInvalidToken() throws InvalidMoveException, GameDoesNotExistException, UnableToJoinGameException {
    final String gameId = gameService.createGame();
    gameService.joinGame(gameId);
    gameService.joinGame(gameId);

    gameService.makeMove(gameId, "1234", 2);
  }

  @Test(expected = InvalidMoveException.class)
  public void makeMoveWhenNotYourTurn() throws InvalidMoveException, GameDoesNotExistException, UnableToJoinGameException {
    final String gameId = gameService.createGame();
    gameService.joinGame(gameId);
    final Player player = gameService.joinGame(gameId);

    gameService.makeMove(gameId, player.getToken(), 11);
  }
}
