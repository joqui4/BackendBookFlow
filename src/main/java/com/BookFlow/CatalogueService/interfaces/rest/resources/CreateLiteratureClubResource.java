package com.BookFlow.CatalogueService.interfaces.rest.resources;

public record CreateLiteratureClubResource(
        String clubName,
        String meetingDate,
        Long bookId,
        String clubDescription,
        Long userId
) {
}
