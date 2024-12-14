package com.BookFlow.CatalogueService.application.internal.eventhandlers;

import com.BookFlow.CatalogueService.domain.model.aggregates.Genre;
import com.BookFlow.CatalogueService.domain.model.commands.PopulateGenreCommand;
import com.BookFlow.CatalogueService.infrastructure.persistence.jpa.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreExistsEventHandler {

    private static final List<PopulateGenreCommand>INITIAL_GENRE = List.of(
            new PopulateGenreCommand(1L, "Legal Thriller"),
            new PopulateGenreCommand(2L, "Historical Fiction"),
            new PopulateGenreCommand(3L, "Fantasy"),
            new PopulateGenreCommand(4L, "Mystery"),
            new PopulateGenreCommand(5L, "Fantasy"),
            new PopulateGenreCommand(6L, "Romance"),
            new PopulateGenreCommand(7L, "Fiction"),
            new PopulateGenreCommand(8L, "Nonfiction"),
            new PopulateGenreCommand(9L, "Biography"),
            new PopulateGenreCommand(10L, "History"),
            new PopulateGenreCommand(11L, "Psychology"),
            new PopulateGenreCommand(12L, "Health"),
            new PopulateGenreCommand(13L, "True Crime")
    );

    @Autowired
    private GenreRepository genreRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void populateGenreTable(){
        for (PopulateGenreCommand command : INITIAL_GENRE){
            if (!genreRepository.existsByName(command.getName())){
                Genre genre = new Genre(command.getId(), command.getName());
                genreRepository.save(genre);
            }
        }
    }
}
