package com.myfi.portfolio.service;

import com.myfi.portfolio.GetNetworthRequest;
import com.myfi.portfolio.GetNetworthReturn;


public class PortfolioApplicationServiceImpl implements PortfolioApplicationService {
    @Override
    public GetNetworthReturn showNetworth(GetNetworthRequest request) {

        return GetNetworthReturn.newBuilder()
                .setTotalNetworth(100.00)
                .setAsOf("05-24-2025")
                .setPercentageGrowth(24)
                .setPercentageInvested(100)
                .setPercentageEmergency(20)
                .setPercentageExpense(100)
                .setTravelFund(5000).
                build();
    }
}
