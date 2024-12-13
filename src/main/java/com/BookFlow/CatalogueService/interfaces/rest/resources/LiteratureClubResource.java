package com.BookFlow.CatalogueService.interfaces.rest.resources;

public record LiteratureClubResource (
        Long clubId,
        String clubName,
        String meetingDate,
        BookResource bookId,
        String clubDescription,
        Long userId

){

}
