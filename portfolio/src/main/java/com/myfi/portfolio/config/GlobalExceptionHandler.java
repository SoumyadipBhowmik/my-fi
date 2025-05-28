package com.myfi.portfolio.config;

import org.springframework.context.annotation.Configuration;

import com.myfi.portfolio.exceptions.AlreadyExistsException;
import com.myfi.portfolio.exceptions.InvalidArgumentException;
import com.myfi.portfolio.exceptions.NotFoundException;
import com.myfi.portfolio.exceptions.ServiceNotAvailableException;

import io.grpc.*;

@Configuration
public class GlobalExceptionHandler implements ServerInterceptor {

  @Override
  public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
      ServerCall<ReqT, RespT> serverCall,
      Metadata metadata,
      ServerCallHandler<ReqT, RespT> serverCallHandler) {

    ServerCall<ReqT, RespT> wrappedCall =
        new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(serverCall) {
          @Override
          public void sendMessage(RespT message) {
            try {
              super.sendMessage(message);
            } catch (Exception e) {
              handleException(e, serverCall, metadata);
            }
          }
        };

    return new ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(
        serverCallHandler.startCall(wrappedCall, metadata)) {
      @Override
      public void onHalfClose() {
        try {
          super.onHalfClose();
        } catch (Exception e) {
          handleException(e, serverCall, metadata);
        }
      }

      @Override
      public void onMessage(ReqT message) {
        try {
          super.onMessage(message);
        } catch (Exception e) {
          handleException(e, serverCall, metadata);
        }
      }
    };
  }

  private <ReqT, RespT> void handleException(
      Exception e, ServerCall<ReqT, RespT> serverCall, Metadata metadata) {

    Status status =
        switch (e) {
          case AlreadyExistsException ignored ->
              Status.ALREADY_EXISTS.withDescription(e.getMessage());
          case InvalidArgumentException ignored ->
              Status.INVALID_ARGUMENT.withDescription(e.getMessage());
          case NotFoundException ignored -> Status.NOT_FOUND.withDescription(e.getMessage());
          case ServiceNotAvailableException ignored ->
              Status.UNAVAILABLE.withDescription(e.getMessage());
          case StatusRuntimeException statusRuntimeException -> statusRuntimeException.getStatus();
          default -> Status.INTERNAL.withDescription("Internal server error occurred");
        };

    serverCall.close(status, metadata);
  }
}
