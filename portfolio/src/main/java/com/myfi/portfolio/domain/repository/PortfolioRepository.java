package com.myfi.portfolio.domain.repository;

import com.myfi.portfolio.domain.models.Portfolio;

public interface PortfolioRepository {

  void save(Portfolio portfolio);
}
