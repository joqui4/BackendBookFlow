package com.BookFlow.usuarios.clubs.domain.model.aggregates;

import com.BookFlow.usuarios.clubs.domain.model.commands.CreateClubCommand;
import com.BookFlow.usuarios.clubs.domain.model.valueobjects.ClubName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Entity
public class Club {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubId;

    @Embedded
    @Column
    private ClubName clubTitle;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment clubCommentId;

    @Getter
    @NotBlank(message = "Club image is mandatory")
    @Column
    private String clubImage;

    @NotBlank(message = "Club description is mandatory")
    @Column
    private String clubDescription;

    @NotBlank(message = "Club author is mandatory")
    @Column
    private String clubAuthor;



    public Club(String clubTitle, Long clubCommentId, String clubImage, String clubDescription, String clubAuthor) {
        this.clubTitle = new ClubName(clubTitle);
        this.clubCommentId = new Comment(clubCommentId, "");
        this.clubImage = clubImage;
        this.clubDescription = clubDescription;
        this.clubAuthor = clubAuthor;

    }

    public Club(CreateClubCommand command, Comment comment) {
        clubTitle = new ClubName(command.clubTitle());
        clubCommentId = comment;
        clubImage = command.clubImage();
        clubDescription = command.clubDescription();
        clubAuthor = command.clubAuthor();

    }

    public Club() {
        this.clubImage = "";
        this.clubDescription = "";
        this.clubAuthor = "";

    }

    public Comment getComment() {
        return clubCommentId;
    }

    public String getClubTitle() {
        return clubTitle.clubTitle();
    }

    public Long getClubId() {
        return clubId;
    }

    public String getClubDescription() {
        return clubDescription;
    }

    public String getClubAuthor() {
        return clubAuthor;
    }


}
