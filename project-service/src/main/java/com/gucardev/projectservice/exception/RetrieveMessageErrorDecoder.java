package com.gucardev.projectservice.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class RetrieveMessageErrorDecoder implements ErrorDecoder {

  private final ErrorDecoder errorDecoder = new Default();

  @Override
  public Exception decode(String methodKey, Response response) {
    ExceptionMessage message = null;
    try (InputStream body = response.body().asInputStream()){
      message = new ExceptionMessage((String) response.headers().get("date").toArray()[0],
              response.status(),
              IOUtils.toString(body, StandardCharsets.UTF_8),
              response.request().url());

    } catch (IOException exception) {
      return new Exception(exception.getMessage());
    }
    return switch (response.status()) {
      case 400 -> new RuntimeException(
          message.message() != null ? message.message() : "Bad Request");
      case 404 -> new EntityNotFoundException(
          message.message() != null ? message.message() : "Not found");
      default -> errorDecoder.decode(methodKey, response);
    };
  }
}
