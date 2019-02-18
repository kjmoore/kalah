package com.kieranjohnmoore.kalah.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
  @RequestMapping("/createGame/{id}")
  public String index(@PathVariable String id) {
    return id;
  }
}
