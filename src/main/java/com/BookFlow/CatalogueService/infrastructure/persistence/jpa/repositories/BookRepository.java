package com.BookFlow.CatalogueService.infrastructure.persistence.jpa.repositories;

import com.BookFlow.CatalogueService.domain.model.aggregates.Book;
import com.BookFlow.CatalogueService.domain.model.aggregates.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    boolean existsByBookTitle(String bookTitle);
    Optional<Book> findByBookTitle(String bookTitle);
    // Nuevo método para buscar libros por nombre del género
    List<Book> findByBookGenreId_Name(String name);
}
