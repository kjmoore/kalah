package com.kieranjohnmoore.kalah.repository;

import com.kieranjohnmoore.kalah.domain.Game;
import com.kieranjohnmoore.kalah.domain.exception.GameDoesNotExistException;

/**
 * Store for ongoing games within the system
 */
public interface GameRepository {
  /**
   * Get a game using its identifier
   * @param id of the game to be retrieved
   * @return the representation of the game
   * @throws GameDoesNotExistException if there is no game with {@code id}
   */
  Game getGame(final String id) throws GameDoesNotExistException;

  /**
   * Create a new game
   * @return a unique identifier for the created game
   */
  String createGame();

  /**
   * Updates a game with a new board layout
   * @param id the identifier for the game
   * @param game the updated game representation
   * @throws GameDoesNotExistException if there is no game with {@code id}
   */
  void updateGame(String id, Game game) throws GameDoesNotExistException;
}
