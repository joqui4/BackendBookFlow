package com.BookFlow.usuarios.clubs.domain.exceptions;

public class CommentNotFoundException extends RuntimeException   {

    public CommentNotFoundException(Long id) {
        super("Comment with id " + id + " not found");
    }
}
