package com.myfi.portfolio.persistence.repository_implementations;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.myfi.portfolio.domain.models.Transactions;
import com.myfi.portfolio.domain.repository.TransactionsRepository;
import com.myfi.portfolio.persistence.entities.TransactionsJpaEntity;
import com.myfi.portfolio.persistence.jpa_repositories.TransactionJpaRepository;
import com.myfi.portfolio.persistence.mappers.TransactionMapper;

@Repository
public class TransactionRepositoryImpl implements TransactionsRepository {

  private final TransactionJpaRepository transactionJpaRepository;
  private final TransactionMapper transactionMapper;

  public TransactionRepositoryImpl(
      TransactionJpaRepository transactionJpaRepository, TransactionMapper transactionMapper) {
    this.transactionJpaRepository = transactionJpaRepository;
    this.transactionMapper = transactionMapper;
  }

  @Override
  public Optional<Transactions> findById(UUID id) {
    return transactionJpaRepository.findById(id).map(transactionMapper::toDomain);
  }

  @Override
  public Transactions save(Transactions transactions) {
    TransactionsJpaEntity entity = new TransactionsJpaEntity();
    entity = transactionJpaRepository.save(entity);
    return transactionMapper.toDomain(entity);
  }
}
