package com.myfi.portfolio.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.myfi.portfolio.domain.commands.CreateTransactionCommand;
import com.myfi.portfolio.domain.commands.UpdateTransactionCommand;
import com.myfi.portfolio.domain.models.Transactions;
import com.myfi.portfolio.domain.repository.TransactionsRepository;
import com.myfi.portfolio.exceptions.InvalidArgumentException;

@Service
public class TransactionService {

  private final TransactionsRepository transactionsRepository;

  public TransactionService(TransactionsRepository transactionsRepository) {
    this.transactionsRepository = transactionsRepository;
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
}
