package com.myfi.portfolio.persistence.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "portfolio")
public class PortfolioJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private UUID userId;

  private double extraCash;

  private double salary;

  private double investment;

  private double expense;

  private double bankBalance;

  private LocalDate transactionData;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
