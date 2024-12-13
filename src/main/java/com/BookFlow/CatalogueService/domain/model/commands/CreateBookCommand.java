package com.BookFlow.CatalogueService.domain.model.commands;

public record CreateBookCommand (String bookTitle,
                                 Long bookGenreId,
                                 String bookImage,
                                 String bookDescription,
                                 String bookAuthor,
                                 String bookAuthorImage,
                                 String bookPublisher,
                                 String bookRank){}
