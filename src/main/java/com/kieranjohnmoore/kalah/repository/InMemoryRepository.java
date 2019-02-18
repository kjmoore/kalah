package com.kieranjohnmoore.kalah.repository;

import com.kieranjohnmoore.kalah.domain.Game;
import com.kieranjohnmoore.kalah.domain.exception.GameDoesNotExistException;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A game repository based around a simple in-memory map, this is intended for low load,
 * single server applications where data loss is acceptable in the case of server restarts
 */
@Service
public class InMemoryRepository implements GameRepository {
  private final Map<String, Game> ongoingGames = new ConcurrentHashMap<>();

  @Override
  public Game getGame(final String id) throws GameDoesNotExistException {
    final Game game = ongoingGames.get(id);
    if (game == null) {
      throw new GameDoesNotExistException("There is no game with this ID");
    }
    return game;
  }

  @Override
  public String createGame() {
    String gameIdentifier = UUID.randomUUID().toString();

    while (ongoingGames.containsKey(gameIdentifier)) {
      gameIdentifier = UUID.randomUUID().toString();
    }

    ongoingGames.put(gameIdentifier, new Game());

    return gameIdentifier;
  }

  @Override
  public void updateGame(final String id, final Game game) throws GameDoesNotExistException {
    final Game oldGame = ongoingGames.get(id);
    if (oldGame == null) {
      throw new GameDoesNotExistException("There is no game with this ID");
    }
    ongoingGames.replace(id, game);
  }
}
