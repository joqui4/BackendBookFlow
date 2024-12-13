package com.BookFlow.usuarios.clubs.rest.transform;

import com.BookFlow.usuarios.clubs.domain.model.aggregates.Comment;
import com.BookFlow.usuarios.clubs.rest.resources.CommentResource;

public class CommentResourceFromEntityAssembler {
    public static CommentResource toResourceFromEntity(Comment comment) {
        return new CommentResource(comment.getCommentId(), comment.getCommentContent());
    }
}
