package com.myfi.portfolio.domain.commands;

import java.util.UUID;

import com.myfi.portfolio.domain.enums.TransactionType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTransactionCommand {

  private UUID userId;
  private TransactionType type;
  private UUID category;
  private double amount;
}
