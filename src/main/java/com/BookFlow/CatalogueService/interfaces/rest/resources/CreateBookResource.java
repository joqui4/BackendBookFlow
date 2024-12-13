package com.BookFlow.CatalogueService.interfaces.rest.resources;

public record CreateBookResource (
        String bookTitle,
        Long bookGenreId,
        String bookImage,
        String bookDescription,
        String bookAuthor,
        String bookAuthorImage,
        String bookPublisher,
        String bookRank
){
}
