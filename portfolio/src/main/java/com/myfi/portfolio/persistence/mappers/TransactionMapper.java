package com.myfi.portfolio.persistence.mappers;

import com.myfi.portfolio.domain.models.Transactions;
import com.myfi.portfolio.persistence.entities.TransactionsJpaEntity;

public class TransactionMapper {

  public TransactionsJpaEntity toEntity(Transactions domain) {
    TransactionsJpaEntity transactions = new TransactionsJpaEntity();
    transactions.setUserId(domain.getUserId());
    transactions.setTransactionType(domain.getType());
    transactions.setCategoryId(domain.getCategoryId());
    transactions.setAmount(domain.getAmount());
    return transactions;
  }

  public Transactions toDomain(TransactionsJpaEntity entity) {
    if (entity == null) {
      return null;
    }
    Transactions transactions = new Transactions();
    transactions.setUserId(entity.getUserId());
    transactions.setType(entity.getTransactionType());
    transactions.setCategoryId(entity.getCategoryId());
    transactions.setAmount(entity.getAmount());
    return transactions;
  }
}
