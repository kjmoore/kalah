package com.kieranjohnmoore.kalah.repository;

import com.kieranjohnmoore.kalah.domain.Game;
import com.kieranjohnmoore.kalah.domain.exception.GameDoesNotExistException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class InMemoryRepositoryTest {
  private InMemoryRepository repo;

  @Before
  public void setup() {
    repo = new InMemoryRepository();
  }

  @Test
  public void createGame() {
    final String game = repo.createGame();
    Assert.assertNotNull("A new game was not returned", game);
    Assert.assertFalse("Game identifier was blank", game.isBlank());
  }

  @Test
  public void retrieveGame() throws GameDoesNotExistException {
    final String id = repo.createGame();

    final Game game = repo.getGame(id);
    Assert.assertNotNull("The game was not returned", game);
    Assert.assertEquals("New game was not initialised",
        "{\"1\":\"6\",\"2\":\"6\",\"3\":\"6\",\"4\":\"6\",\"5\":\"6\",\"6\":\"6\"," +
            "\"7\":\"0\",\"8\":\"6\",\"9\":\"6\",\"10\":\"6\",\"11\":\"6\"," +
            "\"12\":\"6\",\"13\":\"6\",\"14\":\"0\"}", game.getBoard().toString());
  }

  @Test
  public void updateGame() throws GameDoesNotExistException {
    final String id = repo.createGame();
    final Game game = repo.getGame(id);
    game.getBoard().setPitCount(1, 3);
    repo.updateGame(id, game);

    final Game updatedGame = repo.getGame(id);

    Assert.assertNotNull("The game was not returned", updatedGame);
    Assert.assertEquals("New game was not initialised",
        "{\"1\":\"3\",\"2\":\"6\",\"3\":\"6\",\"4\":\"6\",\"5\":\"6\",\"6\":\"6\"," +
            "\"7\":\"0\",\"8\":\"6\",\"9\":\"6\",\"10\":\"6\",\"11\":\"6\"," +
            "\"12\":\"6\",\"13\":\"6\",\"14\":\"0\"}", updatedGame.getBoard().toString());
  }

  @Test(expected = GameDoesNotExistException.class)
  public void retrieveInvalidGame() throws GameDoesNotExistException {
    repo.getGame("1234");
  }

  @Test
  public void checkNoDuplicates() {
    //Testing with a reasonable number of ids isn't viable here due to the time it would take
    //to perform a build, so we are just testing with 10k to ensure it's not doing something
    //clearly wrong.
    Set<String> ids = new HashSet<>();
    for (int i = 0; i < 10000; i++) {
      //Sets will fail to add if one already exists
      Assert.assertTrue("A duplicate was found", ids.add(repo.createGame()));
    }
  }
}
