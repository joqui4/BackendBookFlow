package com.BookFlow.CatalogueService.application.internal.queryservices;

import com.BookFlow.CatalogueService.domain.model.aggregates.LiteratureClub;
import com.BookFlow.CatalogueService.domain.model.queries.*;
import com.BookFlow.CatalogueService.domain.services.LiteratureClubQueryService;
import com.BookFlow.CatalogueService.infrastructure.persistence.jpa.repositories.LiteratureClubRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiteratureClubQueryServiceImpl implements LiteratureClubQueryService {
    private final LiteratureClubRepository literatureClubRepository;

    public LiteratureClubQueryServiceImpl(LiteratureClubRepository literatureClubRepository){
        this.literatureClubRepository = literatureClubRepository;
    }

    @Override
    public Optional<LiteratureClub> handle(GetLiteratureClubByNameQuery query) {
        return literatureClubRepository.findByClubName(query.clubName());
    }
    @Override
    public Optional<LiteratureClub> handle(GetLiteratureClubByIdQuery query) {
        return literatureClubRepository.findById(query.clubId());
    }
    @Override
    public List<LiteratureClub> handle(GetAllLiteratureClubsQuery query) {
        return literatureClubRepository.findAll();
    }
}
