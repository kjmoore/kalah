package com.kieranjohnmoore.kalah.controller;

import com.kieranjohnmoore.kalah.domain.Game;
import com.kieranjohnmoore.kalah.domain.Player;
import com.kieranjohnmoore.kalah.domain.exception.GameDoesNotExistException;
import com.kieranjohnmoore.kalah.domain.exception.InvalidMoveException;
import com.kieranjohnmoore.kalah.domain.exception.UnableToJoinGameException;
import com.kieranjohnmoore.kalah.model.CreateResponse;
import com.kieranjohnmoore.kalah.model.ErrorResponse;
import com.kieranjohnmoore.kalah.model.GameResponse;
import com.kieranjohnmoore.kalah.model.JoinResponse;
import com.kieranjohnmoore.kalah.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
  public static final String PATH = "/games";
  private final GameService gameService;

  @Autowired
  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @RequestMapping(PATH)
  public ResponseEntity create() {
    final String gameId = gameService.createGame();
    return new ResponseEntity<>(new CreateResponse(gameId), HttpStatus.CREATED);
  }

  @RequestMapping(PATH + "/{id}")
  public ResponseEntity showGame(@PathVariable String id) {
    try {
      final Game game = gameService.getGame(id);
      return new ResponseEntity<>(new GameResponse(id, game), HttpStatus.OK);
    } catch (GameDoesNotExistException e) {
      return new ResponseEntity<>(new ErrorResponse(id, "Game does not exist"), HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(PATH + "/{id}/join")
  public ResponseEntity join(@PathVariable String id) {
    try {
      final Player player =  gameService.joinGame(id);
      return new ResponseEntity<>(new JoinResponse(id, player), HttpStatus.CREATED);
    } catch (UnableToJoinGameException e) {
      return new ResponseEntity<>(new ErrorResponse(id, "Game is full"), HttpStatus.FORBIDDEN);
    } catch (GameDoesNotExistException e) {
      return new ResponseEntity<>(new ErrorResponse(id, "Game does not exist"), HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(PATH + "/{id}/pits/{pitId}")
  public ResponseEntity playMove(@PathVariable String id, @PathVariable int pitId,
                      @RequestBody(required = false) String player) {
    if (player == null || player.isBlank()) {
      return new ResponseEntity<>(new ErrorResponse(id, "No Player token provided"), HttpStatus.FORBIDDEN);
    }
    try {
      final Game game = gameService.makeMove(id, player, pitId);
      return new ResponseEntity<>(new GameResponse(id, game), HttpStatus.OK);
    } catch (InvalidMoveException e) {
      return new ResponseEntity<>(new ErrorResponse(id, "This move is invalid"), HttpStatus.BAD_REQUEST);
    } catch (GameDoesNotExistException e) {
      return new ResponseEntity<>(new ErrorResponse(id, "Game does not exist"), HttpStatus.NOT_FOUND);
    }
  }
}
