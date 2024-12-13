package com.BookFlow.usuarios.clubs.rest;

import com.BookFlow.usuarios.clubs.domain.model.queries.GetAllClubsQuery;
import com.BookFlow.usuarios.clubs.domain.model.queries.GetClubByIdQuery;
import com.BookFlow.usuarios.clubs.domain.model.queries.GetClubByNameQuery;
import com.BookFlow.usuarios.clubs.domain.model.valueobjects.ClubName;
import com.BookFlow.usuarios.clubs.domain.services.ClubCommandService;
import com.BookFlow.usuarios.clubs.domain.services.ClubQueryService;
import com.BookFlow.usuarios.clubs.rest.resources.ClubResource;
import com.BookFlow.usuarios.clubs.rest.resources.CreateClubResource;
import com.BookFlow.usuarios.clubs.rest.transform.ClubResourceFromEntityAssembler;
import com.BookFlow.usuarios.clubs.rest.transform.CreateClubCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/v1/clubs", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Club", description="Clubs Management Endpoints")
public class ClubController {
    private final ClubQueryService clubQueryService;
    private final ClubCommandService clubCommandService;

    public ClubController(ClubQueryService clubQueryService, ClubCommandService clubCommandService){
        this.clubQueryService = clubQueryService;
        this.clubCommandService = clubCommandService;
    }

    @PostMapping
    public ResponseEntity<ClubResource> CreateClub(@RequestBody CreateClubResource resource) {
        var createClubCommand = CreateClubCommandFromResourceAssembler.toCommandFromResource(resource);
        var club = clubCommandService.handle(createClubCommand);
        if (club.isEmpty()) return ResponseEntity.badRequest().build();
        var clubResource = ClubResourceFromEntityAssembler.toResourceFromEntity(club.get());
        return new ResponseEntity<>(clubResource, HttpStatus.CREATED);
    }

    @GetMapping("/clubId/{clubId}")
    public ResponseEntity<ClubResource> getClubById(@PathVariable Long clubId) {
        var getClubByIdQuery = new GetClubByIdQuery(clubId);
        var club = clubQueryService.handle(getClubByIdQuery);
        if (club.isEmpty()) return ResponseEntity.badRequest().build();
        var clubResource = ClubResourceFromEntityAssembler.toResourceFromEntity(club.get());
        return ResponseEntity.ok(clubResource);
    }

    @GetMapping("/clubTitle/{name}")
    public ResponseEntity<ClubResource> getClubByName(@PathVariable String name) {
        var nameClub = new ClubName(name);
        var getClubByNameQuery = new GetClubByNameQuery(nameClub);
        var club = clubQueryService.handle(getClubByNameQuery);
        if (club.isEmpty()) return ResponseEntity.badRequest().build();
        var clubResource = ClubResourceFromEntityAssembler.toResourceFromEntity(club.get());
        return ResponseEntity.ok(clubResource);
    }

    @GetMapping
    public ResponseEntity<List<ClubResource>> getAllClubs(){
        var getClubsQuery = new GetAllClubsQuery();
        var clubs = clubQueryService.handle(getClubsQuery);
        var clubResources = clubs.stream().map(ClubResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(clubResources);
    }
}