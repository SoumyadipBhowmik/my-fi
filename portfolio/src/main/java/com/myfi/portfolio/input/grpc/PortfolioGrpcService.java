package com.myfi.portfolio.input.grpc;

import com.myfi.portfolio.*;
import com.myfi.portfolio.service.PortfolioApplicationService;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@AllArgsConstructor
public class PortfolioGrpcService extends PortfolioServiceGrpc.PortfolioServiceImplBase {

  PortfolioApplicationService portfolioService;

  @Override
  public void createTransaction(CreateTransactionRequest request, StreamObserver<TransactionResponse> responseObserver) {
    TransactionResponse response = portfolioService.createTransaction(request);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void showNetworth(
      GetNetworthRequest request, StreamObserver<GetNetworthReturn> responseObserver) {
    GetNetworthReturn response = portfolioService.showNetworth(request);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
