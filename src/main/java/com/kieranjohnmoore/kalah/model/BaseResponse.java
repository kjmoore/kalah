package com.kieranjohnmoore.kalah.model;

import com.kieranjohnmoore.kalah.controller.GameController;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

abstract class BaseResponse {
  private final String id;

  BaseResponse(String id) {
    this.id = id;
  }

  @SuppressWarnings("unused") //Used by Spring
  public String getId() {
    return id;
  }

  @SuppressWarnings("unused") //Used by String
  public String getUri() {
    return ServletUriComponentsBuilder.fromCurrentRequest()
        .replacePath(GameController.PATH + "/" + id)
        .build().toString();
  }
}
