package com.myfi.portfolio.domain.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Networth {

    LocalDate asOf;
    double totalNetworth;
    double emergencyFund;
    double totalInvestment;
    double totalExpense;
    double averageInvestment;
    double averageExpense;
    int percentageInvested;
    int percentageExpense;
    double travelFund;
}
