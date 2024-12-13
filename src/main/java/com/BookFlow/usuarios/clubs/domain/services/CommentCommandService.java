package com.BookFlow.usuarios.clubs.domain.services;

import com.BookFlow.usuarios.clubs.domain.model.aggregates.Comment;
import com.BookFlow.usuarios.clubs.domain.model.commands.CreateCommentCommand;

import java.util.Optional;

public interface CommentCommandService {
    Optional<Comment> handle(CreateCommentCommand command);
}