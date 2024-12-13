package com.BookFlow.CatalogueService.domain.services;

import com.BookFlow.CatalogueService.domain.model.aggregates.Book;
import com.BookFlow.CatalogueService.domain.model.commands.CreateBookCommand;

import java.util.Optional;

public interface BookCommandService {
    Optional<Book> handle(CreateBookCommand command);
}
