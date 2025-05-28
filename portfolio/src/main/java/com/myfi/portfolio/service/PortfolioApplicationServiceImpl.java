package com.myfi.portfolio.service;

import java.util.UUID;

import com.myfi.portfolio.CreateTransactionRequest;
import com.myfi.portfolio.TransactionResponse;
import org.springframework.stereotype.Service;

import com.myfi.portfolio.GetNetworthRequest;
import com.myfi.portfolio.GetNetworthReturn;
import com.myfi.portfolio.persistence.entities.PortfolioJpaEntity;
import com.myfi.portfolio.persistence.jpa_repositories.PortfolioJpaRepository;

@Service
public class PortfolioApplicationServiceImpl implements PortfolioApplicationService {

  PortfolioJpaRepository portfolioJpaRepository;

  public PortfolioApplicationServiceImpl(PortfolioJpaRepository portfolioJpaRepository) {
    this.portfolioJpaRepository = portfolioJpaRepository;
  }

  @Override
  public TransactionResponse createTransaction(CreateTransactionRequest request) {
    return null;
  }

  @Override
  public GetNetworthReturn showNetworth(GetNetworthRequest request) {

    UUID userId = UUID.fromString(request.getUserId());

    PortfolioJpaEntity portfolio = portfolioJpaRepository.findByUserId(userId);

    return GetNetworthReturn.newBuilder()
        .setTotalNetworth(100.00)
        .setAsOf("05-24-2025")
        .setPercentageGrowth(24)
        .setPercentageInvested(100)
        .setPercentageEmergency(20)
        .setPercentageExpense(100)
        .setTravelFund(5000)
        .build();
  }
}
