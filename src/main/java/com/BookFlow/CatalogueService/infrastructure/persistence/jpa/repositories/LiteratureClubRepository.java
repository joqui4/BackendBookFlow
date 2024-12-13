package com.BookFlow.CatalogueService.infrastructure.persistence.jpa.repositories;

import com.BookFlow.CatalogueService.domain.model.aggregates.LiteratureClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LiteratureClubRepository extends JpaRepository<LiteratureClub, Long> {
    boolean existsByClubName(String clubName);
    Optional<LiteratureClub> findByClubName(String clubName);
}
