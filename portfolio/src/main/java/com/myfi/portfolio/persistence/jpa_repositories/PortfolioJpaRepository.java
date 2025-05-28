package com.myfi.portfolio.persistence.jpa_repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myfi.portfolio.persistence.entities.PortfolioJpaEntity;

public interface PortfolioJpaRepository extends JpaRepository<PortfolioJpaEntity, UUID> {
  PortfolioJpaEntity findByUserId(UUID id);
}
