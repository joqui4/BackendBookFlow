package com.BookFlow.usuarios.clubs.application.internal.eventhandlers;

import com.BookFlow.usuarios.clubs.domain.model.aggregates.Comment;
import com.BookFlow.usuarios.clubs.domain.model.commands.PopulateCommentCommand;
import com.BookFlow.usuarios.clubs.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentExistsEventHandler {

    private static final List<PopulateCommentCommand> INITIAL_COMMENT = List.of();

    @Autowired
    private CommentRepository commentRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void populateCommentTable(){
        for (PopulateCommentCommand command : INITIAL_COMMENT){
            if (!commentRepository.existsByName(command.getContent())){
                Comment comment = new Comment(command.getCommentId(), command.getContent());
                commentRepository.save(comment);
            }
        }
    }
}
