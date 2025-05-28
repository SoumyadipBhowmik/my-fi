package com.myfi.portfolio.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.myfi.portfolio.domain.models.Transactions;

public interface TransactionsRepository {

  Optional<Transactions> findById(UUID id);

  Transactions save(Transactions transactions);
}
