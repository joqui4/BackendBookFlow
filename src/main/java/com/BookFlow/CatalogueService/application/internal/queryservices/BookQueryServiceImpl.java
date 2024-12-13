package com.BookFlow.CatalogueService.application.internal.queryservices;

import com.BookFlow.CatalogueService.domain.model.aggregates.Book;
import com.BookFlow.CatalogueService.domain.model.queries.GetAllBooksQuery;
import com.BookFlow.CatalogueService.domain.model.queries.GetBookByGenreQuery;
import com.BookFlow.CatalogueService.domain.model.queries.GetBookByIdQuery;
import com.BookFlow.CatalogueService.domain.model.queries.GetBookByNameQuery;
import com.BookFlow.CatalogueService.domain.services.BookQueryService;
import com.BookFlow.CatalogueService.infrastructure.persistence.jpa.repositories.BookRepository;
import com.BookFlow.CatalogueService.infrastructure.persistence.jpa.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookQueryServiceImpl implements BookQueryService{
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    public BookQueryServiceImpl(BookRepository bookRepository, GenreRepository genreRepository){
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<Book> handle(GetBookByNameQuery query) {
        return bookRepository.findByBookTitle(query.bookTitle());
    }
    @Override
    public Optional<Book> handle(GetBookByIdQuery query) {
        return bookRepository.findById(query.id());
    }
    @Override
    public List<Book> handle(GetBookByGenreQuery query) {
        return bookRepository.findByBookGenreId_Name(query.name());
    }
    @Override
    public List<Book> handle(GetAllBooksQuery query) {
        return bookRepository.findAll();
    }
}
