package com.myfi.portfolio.service;

import com.myfi.portfolio.CreateTransactionRequest;
import com.myfi.portfolio.GetNetworthRequest;
import com.myfi.portfolio.GetNetworthReturn;
import com.myfi.portfolio.TransactionResponse;

public interface PortfolioApplicationService {
  TransactionResponse createTransaction(CreateTransactionRequest request);

  GetNetworthReturn showNetworth(GetNetworthRequest request);
}
