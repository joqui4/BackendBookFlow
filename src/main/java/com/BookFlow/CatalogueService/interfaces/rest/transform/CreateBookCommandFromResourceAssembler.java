package com.BookFlow.CatalogueService.interfaces.rest.transform;

import com.BookFlow.CatalogueService.domain.model.commands.CreateBookCommand;
import com.BookFlow.CatalogueService.interfaces.rest.resources.CreateBookResource;

public class CreateBookCommandFromResourceAssembler {
public static CreateBookCommand toCommandFromResource(CreateBookResource resource) {
        return new CreateBookCommand(
                resource.bookTitle(),
                resource.bookGenreId(),
                resource.bookImage(),
                resource.bookDescription(),
                resource.bookAuthor(),
                resource.bookAuthorImage(),
                resource.bookPublisher(),
                resource.bookRank()
        );
    }
}
