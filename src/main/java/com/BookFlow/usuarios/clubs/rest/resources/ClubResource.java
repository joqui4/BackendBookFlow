package com.BookFlow.usuarios.clubs.rest.resources;

public record ClubResource(
        Long clubId,
        String clubTitle,
        CommentResource clubCommentId,
        String clubImage,
        String clubDescription,
        String clubAuthor
) {
}
