package com.kieranjohnmoore.kalah.controller;

import com.kieranjohnmoore.kalah.domain.exception.GameDoesNotExistException;
import com.kieranjohnmoore.kalah.domain.exception.UnableToJoinGameException;
import com.kieranjohnmoore.kalah.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
  private final GameService gameService;

  @Autowired
  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @RequestMapping("/createGame")
  public String create() {
    return gameService.createGame();
  }

  @RequestMapping("/joinGame/{id}")
  public String join(@PathVariable String id) {
    try {
      return gameService.joinGame(id);
    } catch (UnableToJoinGameException e) {
      return "Game is full";
    } catch (GameDoesNotExistException e) {
      return "Game does not exist";
    }
  }
}
