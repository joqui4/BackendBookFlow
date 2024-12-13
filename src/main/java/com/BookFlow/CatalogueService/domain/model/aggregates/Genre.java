package com.BookFlow.CatalogueService.domain.model.aggregates;

import com.BookFlow.CatalogueService.domain.model.commands.CreateGenreCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
public class Genre  {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;

    @NotBlank(message = "Genre name is mandatory")
    @Column
    private String name;

    public Genre(Long genreId,String name) {
        this.genreId = genreId;
        this.name = name;
    }
    public Genre(CreateGenreCommand command) {
        this.name = command.name();
    }
    public Genre() {
    }

    public Long getGenreId(){
        return genreId;
    }
    public String getName(){
        return name;
    }

}
