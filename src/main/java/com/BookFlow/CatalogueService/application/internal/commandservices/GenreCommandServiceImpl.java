package com.BookFlow.CatalogueService.application.internal.commandservices;

import com.BookFlow.CatalogueService.domain.model.aggregates.Genre;
import com.BookFlow.CatalogueService.domain.model.commands.CreateGenreCommand;
import com.BookFlow.CatalogueService.domain.services.GenreCommandService;
import com.BookFlow.CatalogueService.infrastructure.persistence.jpa.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreCommandServiceImpl implements GenreCommandService {
    private final GenreRepository genreRepository;

    public GenreCommandServiceImpl(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }
    @Override
    public Optional<Genre> handle(CreateGenreCommand command){

        genreRepository.findByName(command.name()).ifPresent(book ->{
            throw new IllegalArgumentException("Genre with already exists");
        });

        var genre = new Genre(command);
        genreRepository.save(genre);
        return Optional.of(genre);
    }
}
