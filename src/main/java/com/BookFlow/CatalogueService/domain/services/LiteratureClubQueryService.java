package com.BookFlow.CatalogueService.domain.services;

import com.BookFlow.CatalogueService.domain.model.aggregates.LiteratureClub;
import com.BookFlow.CatalogueService.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface LiteratureClubQueryService {
    Optional<LiteratureClub> handle(GetLiteratureClubByIdQuery query);
    Optional<LiteratureClub> handle(GetLiteratureClubByNameQuery query);
    List<LiteratureClub> handle(GetAllLiteratureClubsQuery query);
}
