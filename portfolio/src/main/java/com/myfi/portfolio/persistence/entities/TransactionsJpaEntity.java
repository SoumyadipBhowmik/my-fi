package com.myfi.portfolio.persistence.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.myfi.portfolio.domain.enums.TransactionType;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class TransactionsJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private UUID userId;

  private TransactionType transactionType;

  private UUID categoryId;

  private double amount;

  private LocalDate transactionDate;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
