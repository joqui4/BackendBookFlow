package com.BookFlow.usuarios.clubs.application.internal.queryservices;

import com.BookFlow.usuarios.clubs.domain.model.aggregates.Club;
import com.BookFlow.usuarios.clubs.domain.model.queries.GetAllClubsQuery;
import com.BookFlow.usuarios.clubs.domain.model.queries.GetClubByIdQuery;
import com.BookFlow.usuarios.clubs.domain.model.queries.GetClubByNameQuery;
import com.BookFlow.usuarios.clubs.domain.services.ClubQueryService;
import com.BookFlow.usuarios.clubs.repositories.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubQueryServiceImpl implements ClubQueryService {
    private final ClubRepository clubRepository;

    public ClubQueryServiceImpl(ClubRepository clubRepository){
        this.clubRepository = clubRepository;
    }

    @Override
    public Optional<Club> handle(GetClubByNameQuery query) {
        return clubRepository.findByClubTitle(query.clubTitle());
    }

    @Override
    public Optional<Club> handle(GetClubByIdQuery query) {
        return clubRepository.findById(query.id());
    }

    @Override
    public List<Club> handle(GetAllClubsQuery query) {
        return clubRepository.findAll();
    }
}