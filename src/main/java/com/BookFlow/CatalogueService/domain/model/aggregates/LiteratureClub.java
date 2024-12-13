package com.BookFlow.CatalogueService.domain.model.aggregates;

import com.BookFlow.CatalogueService.domain.model.commands.CreateLiteratureClubCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Entity
@Getter
public class LiteratureClub {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubId;
    @NotBlank
    private String clubName;
    @NotBlank
    private String meetingDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book bookId;

    @NotBlank
    private String clubDescription;

    private Long userId;

    public LiteratureClub(String clubName, String meetingDate, Long bookId, String clubDescription, Long userId) {
        this.clubName = clubName;
        this.meetingDate = meetingDate;
        this.bookId = new Book(bookId,"", 0L,"","","","","","");
        this.clubDescription = clubDescription;
        this.userId= userId;
    }
    public LiteratureClub(CreateLiteratureClubCommand command, Book bookId){
        this.clubName = command.clubName();
        this.meetingDate = command.meetingDate();
        this.bookId = bookId;
        this.clubDescription = command.clubDescription();
        this.userId = command.userId();
    }
    public LiteratureClub() {
        this.clubName = "";
        this.meetingDate = "";
        this.clubDescription = "";
    }

    public Book getBook() {
        return bookId;
    }
}
