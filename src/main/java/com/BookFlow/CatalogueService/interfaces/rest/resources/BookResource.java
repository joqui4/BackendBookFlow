package com.BookFlow.CatalogueService.interfaces.rest.resources;

public record BookResource(

    Long bookId,
    String bookTitle,
    GenreResource bookGenreId,
    String bookImage,
    String bookDescription,
    String bookAuthor,
    String bookAuthorImage,
    String bookPublisher,
    String bookRank){

}
