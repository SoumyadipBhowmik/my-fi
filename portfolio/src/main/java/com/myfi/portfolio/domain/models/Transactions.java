package com.myfi.portfolio.domain.models;

import java.util.UUID;

import com.myfi.common.domain.AggregateRoot;
import com.myfi.portfolio.domain.commands.CreateTransactionCommand;
import com.myfi.portfolio.domain.commands.UpdateTransactionCommand;
import com.myfi.portfolio.domain.enums.TransactionType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Transactions extends AggregateRoot<UUID> {

  private UUID userId;
  private TransactionType type;
  private UUID categoryId;
  private double amount;

  public Transactions() {}

  public Transactions(UUID userId, TransactionType type, UUID categoryId, double amount) {
    this.userId = userId;
    this.type = type;
    this.categoryId = categoryId;
    this.amount = amount;
  }

  public Transactions(CreateTransactionCommand command) {
    this.userId = command.getUserId();
    this.type = command.getType();
    this.categoryId = command.getCategory();
    this.amount = command.getAmount();
  }

  public void update(UpdateTransactionCommand command) {

    if (command.getUserId() != null) this.userId = command.getUserId();

    if (command.getType() != null) this.type = command.getType();

    if (command.getCategory() != null) this.categoryId = command.getCategory();

    if (command.getAmount() < 0) this.amount = command.getAmount();
  }
}
