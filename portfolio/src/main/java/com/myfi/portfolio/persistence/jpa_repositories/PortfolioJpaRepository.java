package com.myfi.portfolio.persistence.jpa_repositories;

import com.myfi.portfolio.persistence.entities.PortfolioJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PortfolioJpaRepository extends JpaRepository<PortfolioJpaEntity, UUID> {
    PortfolioJpaEntity findByUserId(UUID id);
}
