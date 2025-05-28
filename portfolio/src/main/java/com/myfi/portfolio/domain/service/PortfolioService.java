package com.myfi.portfolio.domain.service;

import org.springframework.stereotype.Service;

import com.myfi.portfolio.domain.repository.PortfolioRepository;

@Service
public class PortfolioService {

  private final PortfolioRepository portfolioRepository;

  public PortfolioService(PortfolioRepository portfolioRepository) {
    this.portfolioRepository = portfolioRepository;
  }
}
