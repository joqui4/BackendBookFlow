package com.BookFlow.CatalogueService.application.internal.queryservices;

import com.BookFlow.CatalogueService.domain.model.aggregates.Genre;
import com.BookFlow.CatalogueService.domain.model.queries.*;
import com.BookFlow.CatalogueService.domain.services.GenreQueryService;
import com.BookFlow.CatalogueService.infrastructure.persistence.jpa.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreQueryServiceImpl implements GenreQueryService {
    private final GenreRepository genreRepository;

    public GenreQueryServiceImpl(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @Override
    public Optional<Genre> handle(GetGenreByNameQuery query) {
        return genreRepository.findByName(query.name());
    }
    @Override
    public Optional<Genre> handle(GetGenreByIdQuery query) {
        return genreRepository.findById(query.genreId());
    }
    @Override
    public List<Genre> handle(GetAllGenresQuery query) {
        return genreRepository.findAll();
    }
}
