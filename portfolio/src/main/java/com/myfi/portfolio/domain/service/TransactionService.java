package com.myfi.portfolio.domain.service;

import java.time.LocalDate;
import java.util.UUID;

import com.myfi.portfolio.domain.models.Networth;
import com.myfi.portfolio.domain.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

import com.myfi.portfolio.domain.commands.CreateTransactionCommand;
import com.myfi.portfolio.domain.commands.UpdateTransactionCommand;
import com.myfi.portfolio.domain.models.Transactions;
import com.myfi.portfolio.domain.repository.TransactionsRepository;
import com.myfi.portfolio.exceptions.InvalidArgumentException;

@Service
public class TransactionService {

  private final TransactionsRepository transactionsRepository;
  private final PortfolioRepository portfolioRepository;

  public TransactionService(TransactionsRepository transactionsRepository, PortfolioRepository portfolioRepository) {
    this.transactionsRepository = transactionsRepository;
    this.portfolioRepository = portfolioRepository;
  }

  public Transactions createTransaction(CreateTransactionCommand command) {
    if (command.getType() == null) {
      throw new InvalidArgumentException("Need Transaction type");
    }
    Transactions transactions = new Transactions(command);
    return transactionsRepository.save(transactions);
  }

  public void updateTransaction(UpdateTransactionCommand command) {
    UUID transactionId = command.getId();

    Transactions transactions =
        transactionsRepository
            .findById(transactionId)
            .orElseThrow(() -> new InvalidArgumentException("Transaction doesn't exist"));

    transactions.update(command);
  }

  public Networth calculateNetWorth(UUID id) {

    Networth networth = portfolioRepository.findById(id).orElseThrow(() -> new InvalidArgumentException("User not found"));

    double totalNetworth = totalInvestment + emergencyFund;
    networth.setAsOf(LocalDate.now());
    networth.setTotalNetworth(totalNetworth);
    networth.setEmergencyFund(emergencyFund);
    networth.setTotalInvestment(totalInvestment);
    networth.setTotalExpense(totalExpense);
    networth.setAverageInvestment(Math.ceil(totalInvestment));
    networth.setAverageExpense(Math.floor(totalExpense));
    networth.setPercentageInvested(20);
    networth.setPercentageExpense(20);
    networth.setTravelFund(travelFund);

    return portfolioRepository.save(networth);
  }
}
