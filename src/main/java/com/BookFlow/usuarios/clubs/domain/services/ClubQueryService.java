package com.BookFlow.usuarios.clubs.domain.services;

import com.BookFlow.usuarios.clubs.domain.model.aggregates.Club;
import com.BookFlow.usuarios.clubs.domain.model.queries.GetAllClubsQuery;
import com.BookFlow.usuarios.clubs.domain.model.queries.GetClubByIdQuery;
import com.BookFlow.usuarios.clubs.domain.model.queries.GetClubByNameQuery;

import java.util.List;
import java.util.Optional;

public interface ClubQueryService {
    Optional<Club> handle(GetClubByIdQuery query);
    Optional<Club> handle(GetClubByNameQuery query);
    List<Club> handle(GetAllClubsQuery query);
}
