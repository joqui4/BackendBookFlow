package com.BookFlow.CatalogueService.interfaces.rest.transform;

import com.BookFlow.CatalogueService.domain.model.commands.CreateGenreCommand;
import com.BookFlow.CatalogueService.interfaces.rest.resources.CreateGenreResource;

public class CreateGenreCommandFromResourceAssembler {
    public static CreateGenreCommand toCommandFromResource(CreateGenreResource resource) {
        return new CreateGenreCommand(
                resource.name()
        );
    }
}
