package com.kieranjohnmoore.kalah.service;

import com.kieranjohnmoore.kalah.domain.Game;
import com.kieranjohnmoore.kalah.domain.Player;
import com.kieranjohnmoore.kalah.domain.exception.GameDoesNotExistException;
import com.kieranjohnmoore.kalah.domain.exception.InvalidMoveException;
import com.kieranjohnmoore.kalah.domain.exception.UnableToJoinGameException;
import com.kieranjohnmoore.kalah.domain.rule.Rule;
import com.kieranjohnmoore.kalah.repository.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Handles the creation, joining and playing of a Kalah game
 */
@Service
public class GameService {
  private final GameRepository gameRepository;
  private final List<Rule> rules;

  @Autowired
  public GameService(GameRepository gameRepository, List<Rule> rules) {
    this.gameRepository = gameRepository;
    this.rules = rules;
  }

  /**
   * Creates a new game
   * @return unique identifier for the game created
   */
  public String createGame() {
    return gameRepository.createGame();
  }

  /**
   * Joins a game with a unique id, a game can be joined if there are less than two players already
   * in it
   * @param id unique identifier of the game attempting to be joined
   * @return created {@link Player} object who is now a member of this game
   * @throws UnableToJoinGameException if there are already two players in a game
   * @throws GameDoesNotExistException if there is no game with the provided {@code id}
   */
  public Player joinGame(final String id)
      throws UnableToJoinGameException, GameDoesNotExistException {
    final Game game = gameRepository.getGame(id);

    if (game.getPlayer1Token() == null) {
      final String token = UUID.randomUUID().toString();
      game.setPlayer1Token(token);
      return new Player(token, 1);
    } else if (game.getPlayer2Token() == null) {
      String token = UUID.randomUUID().toString();
      final String otherToken = game.getPlayer1Token();

      while (token.equals(otherToken)) {
        token = UUID.randomUUID().toString();
      }

      game.setPlayer2Token(token);
      return new Player(token, 2);
    }
    throw new UnableToJoinGameException("The game is full");
  }

  private boolean isCorrectTurn(Game game, String playerToken) {
    return (game.getPlayerTurn() == 1 && game.getPlayer1Token().equals(playerToken)) ||
        (game.getPlayerTurn() == 2 && game.getPlayer2Token().equals(playerToken));
  }

  /**
   * Makes a move on a game
   * @param id the identifier for the game
   * @param playerToken the unique token for the player attempting to make a move, retreived from
   *                    the {@link Player} object provided by {@code joinGame}
   * @param location the pit to play from
   * @return the board representation calculated after the move
   * @throws InvalidMoveException if the move is not allowed at this point in the game
   * @throws GameDoesNotExistException if the token does not correspond to an active game
   */
  public Game makeMove(final String id, final String playerToken, final int location)
      throws InvalidMoveException, GameDoesNotExistException {
    final Game game = gameRepository.getGame(id);
    if (!isCorrectTurn(game, playerToken)) {
      throw new InvalidMoveException("It is the opponents move");
    }

    for (Rule rule : rules) {
      rule.applyRule(game, location);
    }

    gameRepository.updateGame(id, game);

    return game;
  }

  /**
   * Returns a game in progress
   * @param id the unique identifier for a game
   * @return the game in progress
   * @throws GameDoesNotExistException if no such game exists
   */
  public Game getGame(final String id) throws GameDoesNotExistException {
    return gameRepository.getGame(id);
  }
}
