package com.kieranjohnmoore.kalah;

import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  /**
   * Run a test looking at the native data, don't use internal classes to ensure we aren't
   * testing using broken tools
   */
  @Test
  public void runGame() {
    final String url = "http://localhost:" + port + "/games/";

    final Map create = restTemplate.postForObject(url, "", Map.class);

    final String gameId = (String) create.get("id");

    final Map join1 = restTemplate.postForObject(url + gameId + "/join","", Map.class);
    final Map join2 = restTemplate.postForObject(url + gameId + "/join", "", Map.class);

    final String p1token = (String)((Map)join1.get("player")).get("token");
    final String p2token = (String)((Map)join2.get("player")).get("token");

    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/1", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/2", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/13", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/2", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/12", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/4", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/11", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/5", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/11", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/1", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/12", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/2", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/3", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/9", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/4", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/11", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/1", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/8", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/1", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/12", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/2", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/11", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/4", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/10", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/6", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/13", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/1", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/9", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/12", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/11", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/12", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/2", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/8", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/3", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/6", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/10", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/11", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/5", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/13", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/1", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/10", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/2", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/8", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/5", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/3", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/4", p1token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/9", p2token, String.class));
    prettyPrint(restTemplate.postForObject(url + gameId + "/pits/5", p1token, String.class));

    //Winning Move
    final String move = restTemplate.postForObject(url + gameId + "/pits/6", p1token, String.class);
    prettyPrint(move);

    Assert.assertTrue("Player 2 did not win", move.contains("\"winner\":2"));
    Assert.assertTrue("The final scores were incorrect", move.contains("\"status\":" +
        "{\"1\":\"0\",\"2\":\"0\",\"3\":\"0\",\"4\":\"0\",\"5\":\"0\",\"6\":\"0\",\"7\":\"29\"," +
        "\"8\":\"0\",\"9\":\"0\",\"10\":\"0\",\"11\":\"0\",\"12\":\"0\",\"13\":\"0\"," +
        "\"14\":\"43\"}"));
  }

  /**
   * Print the data in a nice form to allow manual run-through of a game to look for issues.
   * @param string The server response to print
   */
  private void prettyPrint(String string) {
    try {
      final String[] status = new String[14];
      final Map map = new Gson().fromJson(string, Map.class);

      IntStream.range(0, 14).boxed().forEach(i -> {
        final Map data = (Map) map.get("status");
        final String returnedSeeds = (String) data.get(Integer.toString(i + 1));
        status[i] = String.format("%2s", returnedSeeds);
      });

      final String nextTurn = Double.toString((Double) map.get("playerTurn"));
      final String winner = Double.toString((Double) map.get("winner"));

      System.out.println(Arrays.toString(status) + " Next Turn: " + nextTurn + " Winner: " + winner);
    } catch (Exception e) {
      System.out.println(string);
    }
  }
}
