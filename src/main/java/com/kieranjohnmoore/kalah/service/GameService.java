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

@Service
public class GameService {
  private final GameRepository gameRepository;
  private final List<Rule> rules;

  @Autowired
  public GameService(GameRepository gameRepository, List<Rule> rules) {
    this.gameRepository = gameRepository;
    this.rules = rules;
  }

  public String createGame() {
    return gameRepository.createGame();
  }

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

  public String makeMove(final String id, final String playerToken, final int location)
      throws InvalidMoveException, GameDoesNotExistException {
    final Game game = gameRepository.getGame(id);
    if (!isCorrectTurn(game, playerToken)) {
      throw new InvalidMoveException("It is the opponents move");
    }

    for (Rule rule : rules) {
      rule.applyRule(game, location);
    }

    gameRepository.updateGame(id, game);

    return game.getGameState();
  }
}
