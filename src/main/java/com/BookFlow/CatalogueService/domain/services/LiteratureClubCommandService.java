package com.BookFlow.CatalogueService.domain.services;

import com.BookFlow.CatalogueService.domain.model.aggregates.LiteratureClub;
import com.BookFlow.CatalogueService.domain.model.commands.CreateLiteratureClubCommand;

import java.util.Optional;

public interface LiteratureClubCommandService {
    Optional<LiteratureClub>handle(CreateLiteratureClubCommand command);
}
