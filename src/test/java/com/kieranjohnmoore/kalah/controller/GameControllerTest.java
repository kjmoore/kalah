package com.kieranjohnmoore.kalah.controller;

import com.google.gson.Gson;

import com.kieranjohnmoore.kalah.domain.Player;
import com.kieranjohnmoore.kalah.model.CreateResponse;
import com.kieranjohnmoore.kalah.model.ErrorResponse;
import com.kieranjohnmoore.kalah.model.GameResponse;
import com.kieranjohnmoore.kalah.model.JoinResponse;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void startGame() throws Exception {
    final MvcResult result = mockMvc.perform(post("/games"))
        .andExpect(status().isCreated()).andReturn();

    final CreateResponse createResponse = new Gson().fromJson(result.getResponse().getContentAsString(), CreateResponse.class);

    Assert.assertNotNull("The response had no id", createResponse.getId());
    Assert.assertFalse("The response had no id", createResponse.getId().isBlank());

    Assert.assertEquals("The URL was incorrect",
        "http://localhost/games/" + createResponse.getId(), createResponse.getUri());
  }

  @Test
  public void showGame() throws Exception {
    final String gameId = createGame();

    final MvcResult result = mockMvc.perform(post("/games/" + gameId))
        .andExpect(status().isOk()).andReturn();

    final GameResponse showResponse = new Gson().fromJson(result.getResponse().getContentAsString(), GameResponse.class);

    Assert.assertNotNull("The response had no status", showResponse.getStatus());

    Assert.assertEquals("It was not player 1's turn", 1, showResponse.getPlayerTurn());
    Assert.assertEquals("There was already a winner", 0, showResponse.getWinner());

    Assert.assertEquals("The URL was incorrect",
        "http://localhost/games/" + showResponse.getId(), showResponse.getUri());
  }

  @Test
  public void joinGame() throws Exception {
    final String gameId = createGame();

    final MvcResult result = mockMvc.perform(post("/games/" + gameId + "/join"))
        .andExpect(status().isCreated()).andReturn();

    final JoinResponse joinResponse = new Gson().fromJson(result.getResponse().getContentAsString(), JoinResponse.class);

    Assert.assertNotNull("The response had no player", joinResponse.getPlayer());

    final Player player = joinResponse.getPlayer();

    Assert.assertNotNull("Player has no id", player.getToken());
    Assert.assertFalse("Player has no id", player.getToken().isBlank());
    Assert.assertEquals("Player was not player 1", 1, player.getPlayerNumber());

    Assert.assertEquals("The ids did not match", gameId, joinResponse.getId());
    Assert.assertEquals("The URL was incorrect",
        "http://localhost/games/" + joinResponse.getId(), joinResponse.getUri());
  }

  @Test
  public void playMove() throws Exception {
    final String gameId = createGame();

    final MvcResult result = mockMvc.perform(post("/games/" + gameId + "/join"))
        .andExpect(status().isCreated()).andReturn();

    final JoinResponse joinResponse = new Gson().fromJson(result.getResponse().getContentAsString(), JoinResponse.class);
    final Player player = joinResponse.getPlayer();

    final MvcResult result2 = mockMvc.perform(post("/games/" + gameId + "/pits/5").content(player.getToken()))
        .andExpect(status().isOk()).andReturn();

    final GameResponse showResponse = new Gson().fromJson(result2.getResponse().getContentAsString(), GameResponse.class);

    Assert.assertNotNull("The response had no status", showResponse.getStatus());
  }

  @Test
  public void illegalJoin() throws Exception {
    final String gameId = createGame();

    mockMvc.perform(post("/games/" + gameId + "/join")).andExpect(status().isCreated());
    mockMvc.perform(post("/games/" + gameId + "/join")).andExpect(status().isCreated());
    final MvcResult mvcResult = mockMvc.perform(post("/games/" + gameId + "/join"))
        .andExpect(status().isForbidden()).andReturn();

    final ErrorResponse error =
        new Gson().fromJson(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);

    Assert.assertEquals("The ids did not match", gameId, error.getId());
    Assert.assertEquals("The URL was incorrect",
        "http://localhost/games/" + error.getId(), error.getUri());
    Assert.assertEquals("The error did not match", error.getError(), "Game is full");
  }

  @Test
  public void gameNotFound() throws Exception {
    final String gameId = "1234";

    final MvcResult mvcResult = mockMvc.perform(post("/games/" + gameId + "/join"))
        .andExpect(status().isNotFound()).andReturn();

    final ErrorResponse error =
        new Gson().fromJson(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);

    Assert.assertEquals("The ids did not match", gameId, error.getId());
    Assert.assertEquals("The URL was incorrect",
        "http://localhost/games/" + error.getId(), error.getUri());
    Assert.assertEquals("The error did not match", error.getError(), "Game does not exist");
  }

  private String createGame() throws Exception {
    final MvcResult result = mockMvc.perform(post("/games"))
        .andExpect(status().isCreated()).andReturn();

    final CreateResponse createResponse = new Gson().fromJson(result.getResponse().getContentAsString(), CreateResponse.class);
    return createResponse.getId();
  }
}