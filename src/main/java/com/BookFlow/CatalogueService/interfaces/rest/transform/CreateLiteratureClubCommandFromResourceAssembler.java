package com.BookFlow.CatalogueService.interfaces.rest.transform;

import com.BookFlow.CatalogueService.domain.model.commands.CreateLiteratureClubCommand;
import com.BookFlow.CatalogueService.interfaces.rest.resources.CreateLiteratureClubResource;

public class CreateLiteratureClubCommandFromResourceAssembler {
    public static CreateLiteratureClubCommand toCommandFromResource(CreateLiteratureClubResource resource) {
        return new CreateLiteratureClubCommand(
                resource.clubName(),
                resource.meetingDate(),
                resource.bookId(),
                resource.clubDescription(),
                resource.userId()
        );
    }
}
