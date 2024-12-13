package com.BookFlow.CatalogueService.domain.model.aggregates;

import com.BookFlow.CatalogueService.domain.model.commands.CreateBookCommand;
import com.BookFlow.usuarios.clubs.domain.model.aggregates.Comment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity

public class Book  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column
    private String bookTitle;

    @ManyToOne // Relación muchos a uno de Book a Genre eso quiere decir que un libro pertenece a un género
    @JoinColumn(name = "genre_id", nullable = false)

    private Genre bookGenreId;

    @Getter
    @NotBlank(message = "Book image is mandatory")
    @Column
    private String bookImage;

    @NotBlank (message = "Book description is mandatory")
    @Column
    private String bookDescription;

    @NotBlank(message = "Book author is mandatory")
    @Column
    private String bookAuthor;

    @Column
    private String bookAuthorImage;

    @Column
    private String bookPublisher;

    @NotNull
    @Column
    private String bookRank;
/*
    @OneToMany
    @JoinColumn(name = "comment_id")
    private List<Comment> commentId;
*/
    public Book(String bookTitle, Long bookGenreId, String bookImage, String bookDescription, String bookAuthor, String bookAuthorImage, String bookPublisher, String bookRank) {
        this.bookTitle = bookTitle;
        this.bookGenreId = new Genre(bookGenreId,"");
        this.bookImage = bookImage;
        this.bookDescription = bookDescription;
        this.bookAuthor = bookAuthor;
        this.bookAuthorImage = bookAuthorImage;
        this.bookPublisher = bookPublisher;
        this.bookRank = bookRank;
     //   this.commentId = List.of(new Comment(commentId,""));
    }
    public Book(Long bookId,String bookTitle, Long bookGenreId, String bookImage, String bookDescription, String bookAuthor, String bookAuthorImage, String bookPublisher, String bookRank) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookGenreId = new Genre(bookGenreId,"");
        this.bookImage = bookImage;
        this.bookDescription = bookDescription;
        this.bookAuthor = bookAuthor;
        this.bookAuthorImage = bookAuthorImage;
        this.bookPublisher = bookPublisher;
        this.bookRank = bookRank;
     //   this.commentId = List.of(new Comment(commentId,""));
    }
    public Book(CreateBookCommand command, Genre genre){
       bookTitle = command.bookTitle();
       bookGenreId = genre;
       bookImage = command.bookImage();
       bookDescription = command.bookDescription();
       bookAuthor = command.bookAuthor();
       bookAuthorImage = command.bookAuthorImage();
       bookPublisher = command.bookPublisher();
       bookRank = command.bookRank();
   //    commentId = List.of(comment);

    }
    public Book() {
        this.bookImage = "";
        this.bookDescription = "";
        this.bookAuthor = "";
        this.bookAuthorImage = "";
        this.bookPublisher = "";
        this.bookRank = "";
    }

    public Genre getGenre(){
        return bookGenreId;
    }
    public String getBookName(){
        return bookTitle;
    }
}
