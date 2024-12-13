package com.BookFlow.CatalogueService.domain.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id){
        super("Book with id " + id + " not found");
    }
}
