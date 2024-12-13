package com.BookFlow.CatalogueService.domain.services;

import com.BookFlow.CatalogueService.domain.model.aggregates.Genre;
import com.BookFlow.CatalogueService.domain.model.commands.CreateGenreCommand;

import java.util.Optional;

public interface GenreCommandService {
    Optional<Genre> handle(CreateGenreCommand command);
}