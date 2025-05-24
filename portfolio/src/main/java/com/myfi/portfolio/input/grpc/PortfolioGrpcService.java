package com.myfi.portfolio.input.grpc;

import com.myfi.portfolio.GetNetworthRequest;
import com.myfi.portfolio.GetNetworthReturn;
import com.myfi.portfolio.PortfolioServiceGrpc;
import com.myfi.portfolio.service.PortfolioApplicationService;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class PortfolioGrpcService extends PortfolioServiceGrpc.PortfolioServiceImplBase {

    PortfolioApplicationService portfolioService;

    @Override
    public void showNetworth(GetNetworthRequest request, StreamObserver<GetNetworthReturn> responseObserver) {
        GetNetworthReturn response = portfolioService.showNetworth(request);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
