package com.BookFlow.CatalogueService.domain.model.commands;

public class PopulateGenreCommand {
    private final Long id;
    private final String name;

    public PopulateGenreCommand(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
