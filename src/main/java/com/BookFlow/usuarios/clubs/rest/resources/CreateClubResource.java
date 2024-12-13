package com.BookFlow.usuarios.clubs.rest.resources;

public record CreateClubResource(
        String clubTitle,
        Long clubCommentId,
        String clubImage,
        String clubDescription,
        String clubAuthor
) {
}
