package com.BookFlow.CatalogueService.interfaces.rest.transform;

import com.BookFlow.CatalogueService.domain.model.aggregates.LiteratureClub;
import com.BookFlow.CatalogueService.interfaces.rest.resources.LiteratureClubResource;

public class LiteratureClubResourceFromEntityAssembler {
    public static LiteratureClubResource toResourceFromEntity (LiteratureClub literatureClub) {
        return new LiteratureClubResource(
                literatureClub.getClubId(),
                literatureClub.getClubName(),
                literatureClub.getMeetingDate(),
                BookResourceFromEntityAssembler.toResourceFromEntity(literatureClub.getBook()),
                literatureClub.getClubDescription(),
                literatureClub.getUserId()
        );
    }
}
