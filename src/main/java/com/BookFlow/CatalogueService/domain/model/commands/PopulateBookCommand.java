package com.BookFlow.CatalogueService.domain.model.commands;

import com.BookFlow.CatalogueService.domain.model.aggregates.Genre;


public class PopulateBookCommand {
    private Long bookId;

    private String bookTitle;


    private Genre bookGenreId;

    private String bookImage;


    private String bookDescription;


    private String bookAuthor;

    private String bookAuthorImage;

    private String bookPublisher;
    private String bookRank;

    public PopulateBookCommand(Long bookId, String bookTitle, Long bookGenreId, String bookImage, String bookDescription, String bookAuthor, String bookAuthorImage, String bookPublisher,String bookRank) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookGenreId = new Genre(bookGenreId,"");
        this.bookImage = bookImage;
        this.bookDescription = bookDescription;
        this.bookAuthor = bookAuthor;
        this.bookAuthorImage = bookAuthorImage;
        this.bookPublisher = bookPublisher;
        this.bookRank = bookRank;
    }

    public Long getBookId() {
        return bookId;
    }
    public String getBookTitle() {
        return bookTitle;
    }
    public Long getBookGenreId() {
        return bookGenreId.getGenreId();
    }
    public String getBookImage() {
        return bookImage;
    }
    public String getBookDescription() {
        return bookDescription;
    }
    public String getBookAuthor() {
        return bookAuthor;
    }
    public String getBookAuthorImage() {
        return bookAuthorImage;
    }
    public String getBookPublisher() {
        return bookPublisher;
    }
    public String getBookRank() {
        return bookRank;
    }
}
