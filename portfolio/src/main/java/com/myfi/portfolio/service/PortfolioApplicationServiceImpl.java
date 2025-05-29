package com.myfi.portfolio.service;

import java.util.UUID;

import com.myfi.portfolio.domain.models.Networth;
import com.myfi.portfolio.persistence.mappers.PortfolioMapper;
import org.springframework.stereotype.Service;

import com.myfi.portfolio.CreateTransactionRequest;
import com.myfi.portfolio.GetNetworthRequest;
import com.myfi.portfolio.GetNetworthReturn;
import com.myfi.portfolio.TransactionResponse;
import com.myfi.portfolio.domain.service.TransactionService;
import com.myfi.portfolio.persistence.entities.PortfolioJpaEntity;
import com.myfi.portfolio.persistence.jpa_repositories.PortfolioJpaRepository;
import com.myfi.portfolio.persistence.mappers.TransactionMapper;

@Service
public class PortfolioApplicationServiceImpl implements PortfolioApplicationService {

  private final PortfolioJpaRepository portfolioJpaRepository;
  private final TransactionService transactionService;
  private final TransactionMapper transactionMapper;
  private final PortfolioMapper portfolioMapper;

  public PortfolioApplicationServiceImpl(
      PortfolioJpaRepository portfolioJpaRepository,
      TransactionService transactionService,
      TransactionMapper transactionMapper,
      PortfolioMapper portfolioMapper) {
    this.portfolioJpaRepository = portfolioJpaRepository;
    this.transactionService = transactionService;
    this.transactionMapper = transactionMapper;
    this.portfolioMapper = portfolioMapper;
  }

  @Override
  public TransactionResponse createTransaction(CreateTransactionRequest request) {

    return null;
  }

  @Override
  public GetNetworthReturn showNetworth(GetNetworthRequest request) {

    UUID userId = UUID.fromString(request.getUserId());

    Networth networth = transactionService.calculateNetWorth(userId);
    PortfolioJpaEntity entity = portfolioMapper.toEntity(networth);


    return GetNetworthReturn.newBuilder()
        .setAsOf("entity.getAs")
        .setTotalNetworth(123456.00)
        .setEmergencyFund(37000.00)
        .setTotalInvestment(106210.00)
        .setTotalExpense(61231.00)
        .setAverageInvestment(26000.00)
        .setAverageExpense(15299.00)
        .setPercentageInvested(54)
        .setPercentageExpense(22)
        .setTravelFund(5000.00)
        .build();
  }
}
