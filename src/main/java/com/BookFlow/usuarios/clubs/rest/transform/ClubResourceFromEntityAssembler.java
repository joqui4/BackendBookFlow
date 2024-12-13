package com.BookFlow.usuarios.clubs.rest.transform;

import com.BookFlow.usuarios.clubs.domain.model.aggregates.Club;
import com.BookFlow.usuarios.clubs.rest.resources.ClubResource;

public class ClubResourceFromEntityAssembler {
    public static ClubResource toResourceFromEntity(Club club) {
        return new ClubResource(
                club.getClubId(),
                club.getClubTitle(),
                CommentResourceFromEntityAssembler.toResourceFromEntity(club.getComment()),
                club.getClubImage(),
                club.getClubDescription(),
                club.getClubAuthor()
        );
    }
}
