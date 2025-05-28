package com.myfi.portfolio.service;

import com.myfi.portfolio.GetNetworthRequest;
import com.myfi.portfolio.GetNetworthReturn;

public interface PortfolioApplicationService {
  GetNetworthReturn showNetworth(GetNetworthRequest request);
}
