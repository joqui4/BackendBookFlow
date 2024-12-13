package com.BookFlow.CatalogueService.application.internal.eventhandlers;

import com.BookFlow.CatalogueService.domain.model.aggregates.Book;
import com.BookFlow.CatalogueService.domain.model.commands.PopulateBookCommand;
import com.BookFlow.CatalogueService.infrastructure.persistence.jpa.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookExistsEventHandler {
    private static final List<PopulateBookCommand> INITIAL_BOOKS = List.of();

    @Autowired
    private BookRepository bookRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void populateGenreTable(){
        for (PopulateBookCommand command : INITIAL_BOOKS){
            if (!bookRepository.existsByBookTitle(command.getBookTitle())) {
                Book book = new Book(command.getBookId(),command.getBookTitle(),command.getBookGenreId(),command.getBookImage(),command.getBookDescription(),command.getBookAuthor(),command.getBookAuthorImage(),command.getBookPublisher(),command.getBookRank());
                bookRepository.save(book);
            }
        }
    }
}
