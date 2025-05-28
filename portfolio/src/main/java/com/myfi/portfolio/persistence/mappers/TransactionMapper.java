package com.myfi.portfolio.persistence.mappers;

import com.myfi.portfolio.domain.models.Transactions;
import com.myfi.portfolio.persistence.entities.TransactionsJpaEntity;

public class TransactionMapper {

  public Transactions toDomain(TransactionsJpaEntity entity) {
    if (entity == null) {
      return null;
    }
    Transactions transactions = new Transactions();
    transactions.setUserId(entity.getUserId());
    transactions.setType(entity.getTransactionType());
    transactions.setCategory(entity.getCategoryId());
    transactions.setAmount(entity.getAmount());

    return transactions;
  }
}
