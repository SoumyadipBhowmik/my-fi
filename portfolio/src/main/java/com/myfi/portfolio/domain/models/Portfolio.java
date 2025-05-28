package com.myfi.portfolio.domain.models;

import java.util.Date;
import java.util.UUID;

import com.myfi.common.domain.AggregateRoot;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Portfolio extends AggregateRoot<UUID> {

  private double extraCash;
  private double salary;
  private double investment;
  private double expense;
  private double bankBalance;
  private Date transactionDate;

  public Portfolio(
      double extraCash,
      double salary,
      double investment,
      double expense,
      double bankBalance,
      Date transactionDate) {
    this.extraCash = extraCash;
    this.salary = salary;
    this.investment = investment;
    this.expense = expense;
    this.bankBalance = bankBalance;
    this.transactionDate = transactionDate;
  }
}
