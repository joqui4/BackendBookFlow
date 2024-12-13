package com.BookFlow.usuarios.clubs.application.internal.queryservices;

import com.BookFlow.usuarios.clubs.domain.model.aggregates.Comment;
import com.BookFlow.usuarios.clubs.domain.model.queries.GetAllCommentsQuery;
import com.BookFlow.usuarios.clubs.domain.model.queries.GetCommentByIdQuery;
import com.BookFlow.usuarios.clubs.domain.model.queries.GetCommentByNameQuery;
import com.BookFlow.usuarios.clubs.domain.services.CommentQueryService;
import com.BookFlow.usuarios.clubs.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentQueryServiceImpl implements CommentQueryService {
    private final CommentRepository commentRepository;

    public CommentQueryServiceImpl(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @Override
    public Optional<Comment> handle(GetCommentByNameQuery query) {
        return commentRepository.findByName(query.content());
    }

    @Override
    public Optional<Comment> handle(GetCommentByIdQuery query) {
        return commentRepository.findById(query.commentId());
    }

    @Override
    public List<Comment> handle(GetAllCommentsQuery query) {
        return commentRepository.findAll();
    }
}
