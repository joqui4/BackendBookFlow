package com.BookFlow.usuarios.clubs.domain.services;

import com.BookFlow.usuarios.clubs.domain.model.aggregates.Club;
import com.BookFlow.usuarios.clubs.domain.model.commands.CreateClubCommand;

import java.util.Optional;

public interface ClubCommandService {
    Optional<Club> handle(CreateClubCommand command);
}
