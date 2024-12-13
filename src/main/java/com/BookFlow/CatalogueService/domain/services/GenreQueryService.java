package com.BookFlow.CatalogueService.domain.services;

import com.BookFlow.CatalogueService.domain.model.aggregates.Genre;
import com.BookFlow.CatalogueService.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface GenreQueryService {
    Optional<Genre> handle(GetGenreByIdQuery query);
    Optional<Genre> handle(GetGenreByNameQuery query);
    List<Genre> handle(GetAllGenresQuery query);
}
