package com.BookFlow.CatalogueService.domain.model.aggregates;

import com.BookFlow.CatalogueService.domain.model.commands.CreateGenreCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Entity
public class Genre  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Genre name is mandatory")
    @Column
    private String name;

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Genre(CreateGenreCommand command) {
        this.name = command.name();
    }
    public Genre() {
    }

}
