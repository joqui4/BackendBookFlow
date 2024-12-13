package com.BookFlow.usuarios.clubs.application.internal.commandservices;

import com.BookFlow.usuarios.clubs.domain.exceptions.CommentNotFoundException;
import com.BookFlow.usuarios.clubs.domain.model.aggregates.Club;
import com.BookFlow.usuarios.clubs.domain.model.aggregates.Comment;
import com.BookFlow.usuarios.clubs.domain.model.commands.CreateClubCommand;
import com.BookFlow.usuarios.clubs.domain.model.valueobjects.ClubName;
import com.BookFlow.usuarios.clubs.domain.services.ClubCommandService;
import com.BookFlow.usuarios.clubs.repositories.ClubRepository;
import com.BookFlow.usuarios.clubs.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClubCommandServiceImpl implements ClubCommandService {
    private final ClubRepository clubRepository;
    private final CommentRepository commentRepository;

    public ClubCommandServiceImpl(ClubRepository clubRepository, CommentRepository commentRepository){
        this.clubRepository = clubRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Optional<Club> handle(CreateClubCommand command){
        Comment comment = commentRepository.findById(command.clubCommentId()).orElseThrow(() -> new CommentNotFoundException(command.clubCommentId()));

        var clubTitle = new ClubName(command.clubTitle());
        clubRepository.findByClubTitle(clubTitle).ifPresent(club ->{
            throw new IllegalArgumentException("Club with content already exists");
        });

        var club = new Club(command, comment);
        clubRepository.save(club);
        return Optional.of(club);
    }
}