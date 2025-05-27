package com.myfi.portfolio.exceptions;

import io.grpc.Status;
import lombok.Getter;

@Getter
public class ServiceNotAvailableException extends RuntimeException {

  private static final Status.Code statusCode = Status.Code.UNAVAILABLE;

  public ServiceNotAvailableException(String message) {
    super(message);
  }

}
