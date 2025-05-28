package com.myfi.portfolio.service;

import java.util.UUID;

import com.myfi.portfolio.CreateTransactionRequest;
import com.myfi.portfolio.TransactionResponse;
import com.myfi.portfolio.domain.models.Transactions;
import com.myfi.portfolio.domain.service.TransactionService;
import com.myfi.portfolio.persistence.entities.TransactionsJpaEntity;
import com.myfi.portfolio.persistence.mappers.TransactionMapper;
import org.springframework.stereotype.Service;

import com.myfi.portfolio.GetNetworthRequest;
import com.myfi.portfolio.GetNetworthReturn;
import com.myfi.portfolio.persistence.entities.PortfolioJpaEntity;
import com.myfi.portfolio.persistence.jpa_repositories.PortfolioJpaRepository;

@Service
public class PortfolioApplicationServiceImpl implements PortfolioApplicationService {

  private final PortfolioJpaRepository portfolioJpaRepository;
  private final TransactionService transactionService;
  private final TransactionMapper transactionMapper;

  public PortfolioApplicationServiceImpl(PortfolioJpaRepository portfolioJpaRepository, TransactionService transactionService, TransactionMapper transactionMapper) {
    this.portfolioJpaRepository = portfolioJpaRepository;
    this.transactionService = transactionService;
    this.transactionMapper = transactionMapper;
  }

  @Override
  public TransactionResponse createTransaction(CreateTransactionRequest request) {

    TransactionsJpaEntity entity = new TransactionsJpaEntity();
    return null;
//    Transactions transactions = transactionService.createTransaction(transactionMapper.toDomain(entity));
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
