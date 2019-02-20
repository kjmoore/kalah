package com.kieranjohnmoore.kalah.model;

import com.kieranjohnmoore.kalah.domain.Player;

public class JoinResponse extends BaseResponse {
  private final Player player;

  public JoinResponse(String id, Player player) {
    super(id);
    this.player = player;
  }

  @SuppressWarnings("Unused") //Used by Spring
  public Player getPlayer() {
    return player;
  }
}
