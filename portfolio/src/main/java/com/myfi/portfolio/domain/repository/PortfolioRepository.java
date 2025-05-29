package com.myfi.portfolio.domain.repository;

import com.myfi.portfolio.domain.models.Networth;

import java.util.Optional;
import java.util.UUID;

public interface PortfolioRepository {

    Networth save(Networth networth);

    Optional<Networth> findById(UUID id);
}
