package com.BookFlow.CatalogueService.application.internal.commandservices;

import com.BookFlow.CatalogueService.domain.exceptions.GenreNotFoundException;
import com.BookFlow.CatalogueService.domain.model.aggregates.Book;
import com.BookFlow.CatalogueService.domain.model.aggregates.Genre;
import com.BookFlow.CatalogueService.domain.model.commands.CreateBookCommand;
import com.BookFlow.CatalogueService.domain.services.BookCommandService;
import com.BookFlow.CatalogueService.infrastructure.persistence.jpa.repositories.BookRepository;
import com.BookFlow.CatalogueService.infrastructure.persistence.jpa.repositories.GenreRepository;
import com.BookFlow.usuarios.clubs.domain.model.aggregates.Comment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookCommandServiceImpl implements BookCommandService{
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    public BookCommandServiceImpl(BookRepository bookRepository, GenreRepository genreRepository){
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public Optional<Book>handle(CreateBookCommand command){
        Genre genre = genreRepository.findById(command.bookGenreId()).orElseThrow(() -> new GenreNotFoundException(command.bookGenreId()));
        //Comment comment = new Comment(command.commentId());
        bookRepository.findByBookTitle(command.bookTitle()).ifPresent(book ->{
            throw new IllegalArgumentException("Book with name already exists");
        });

        var book = new Book(command, genre);
        bookRepository.save(book);
        return Optional.of(book);
    }
}
