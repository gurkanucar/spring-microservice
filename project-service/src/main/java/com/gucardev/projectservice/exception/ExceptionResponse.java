package com.gucardev.projectservice.exception;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class ExceptionResponse {
  @SerializedName("error")
  private String errorMessage;

  public ExceptionResponse(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public static ExceptionResponse fromJson(String json) {
    Gson gson = new Gson();
    return gson.fromJson(json, ExceptionResponse.class);
  }
}
