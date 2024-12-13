package com.BookFlow.usuarios.clubs.application.internal.commandservices;

import com.BookFlow.usuarios.clubs.domain.model.aggregates.Comment;
import com.BookFlow.usuarios.clubs.domain.model.commands.CreateCommentCommand;
import com.BookFlow.usuarios.clubs.domain.services.CommentCommandService;
import com.BookFlow.usuarios.clubs.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentCommandServiceImpl implements CommentCommandService {
    private final CommentRepository commentRepository;

    public CommentCommandServiceImpl(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @Override
    public Optional<Comment> handle(CreateCommentCommand command){
        commentRepository.findByName(command.content()).ifPresent(comment ->{
            throw new IllegalArgumentException("Comment with content already exists");
        });

        var newComment = new Comment(command);
        commentRepository.save(newComment);
        return Optional.of(newComment);
    }
}