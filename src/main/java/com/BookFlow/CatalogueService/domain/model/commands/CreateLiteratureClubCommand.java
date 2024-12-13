package com.BookFlow.CatalogueService.domain.model.commands;

public record CreateLiteratureClubCommand(String clubName, String meetingDate, Long bookId, String clubDescription, Long userId){
}
