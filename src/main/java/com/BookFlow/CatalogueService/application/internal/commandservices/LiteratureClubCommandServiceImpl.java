package com.BookFlow.CatalogueService.application.internal.commandservices;

import com.BookFlow.CatalogueService.domain.exceptions.BookNotFoundException;
import com.BookFlow.CatalogueService.domain.exceptions.GenreNotFoundException;
import com.BookFlow.CatalogueService.domain.model.aggregates.Book;
import com.BookFlow.CatalogueService.domain.model.aggregates.Genre;
import com.BookFlow.CatalogueService.domain.model.aggregates.LiteratureClub;
import com.BookFlow.CatalogueService.domain.model.commands.CreateBookCommand;
import com.BookFlow.CatalogueService.domain.model.commands.CreateLiteratureClubCommand;
import com.BookFlow.CatalogueService.domain.services.LiteratureClubCommandService;
import com.BookFlow.CatalogueService.infrastructure.persistence.jpa.repositories.BookRepository;
import com.BookFlow.CatalogueService.infrastructure.persistence.jpa.repositories.LiteratureClubRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LiteratureClubCommandServiceImpl implements LiteratureClubCommandService {
    private final LiteratureClubRepository literatureClubRepository;
    private final BookRepository bookRepository;

    public LiteratureClubCommandServiceImpl(LiteratureClubRepository literatureClubRepository, BookRepository bookRepository){
        this.literatureClubRepository = literatureClubRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<LiteratureClub> handle(CreateLiteratureClubCommand command){
        Book book = bookRepository.findById(command.bookId()).orElseThrow(() -> new BookNotFoundException(command.bookId()));
        literatureClubRepository.findByClubName(command.clubName()).ifPresent(literatureClub ->{
            throw new IllegalArgumentException("Book with name already exists");
        });

        var literatureClub = new LiteratureClub(command,book);
        literatureClubRepository.save(literatureClub);
        return Optional.of(literatureClub);
    }
}
