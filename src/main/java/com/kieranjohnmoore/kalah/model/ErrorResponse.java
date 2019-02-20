package com.kieranjohnmoore.kalah.model;

public class ErrorResponse extends BaseResponse {
  private final String error;

  public ErrorResponse(String id, final String error) {
    super(id);
    this.error = error;
  }

  @SuppressWarnings("unused") //Used by Spring
  public String getError() {
    return error;
  }
}
