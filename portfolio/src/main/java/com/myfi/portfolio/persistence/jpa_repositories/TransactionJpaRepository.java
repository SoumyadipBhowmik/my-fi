package com.myfi.portfolio.persistence.jpa_repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myfi.portfolio.persistence.entities.TransactionsJpaEntity;

public interface TransactionJpaRepository extends JpaRepository<TransactionsJpaEntity, UUID> {}
