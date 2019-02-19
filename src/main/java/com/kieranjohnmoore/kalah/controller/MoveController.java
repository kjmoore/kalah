package com.kieranjohnmoore.kalah.controller;

import com.kieranjohnmoore.kalah.domain.exception.GameDoesNotExistException;
import com.kieranjohnmoore.kalah.domain.exception.InvalidMoveException;
import com.kieranjohnmoore.kalah.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoveController {
  private final GameService gameService;

  @Autowired
  public MoveController(GameService gameService) {
    this.gameService = gameService;
  }

  @RequestMapping("/makeMove/{gameId}/{player}/pits/{pit}")
  public String index(@PathVariable String gameId, @PathVariable int pit,
                      @PathVariable String player) {
    try {
      return gameService.makeMove(gameId, player, pit).toString();
    } catch (InvalidMoveException e) {
      return "This move is invalid";
    } catch (GameDoesNotExistException e) {
      return "The game does not exist";
    }
  }
}
