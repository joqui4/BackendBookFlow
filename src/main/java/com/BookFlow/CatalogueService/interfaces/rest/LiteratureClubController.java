package com.BookFlow.CatalogueService.interfaces.rest;

import com.BookFlow.CatalogueService.domain.model.queries.GetAllLiteratureClubsQuery;
import com.BookFlow.CatalogueService.domain.model.queries.GetLiteratureClubByIdQuery;
import com.BookFlow.CatalogueService.domain.model.queries.GetLiteratureClubByNameQuery;
import com.BookFlow.CatalogueService.domain.services.LiteratureClubCommandService;
import com.BookFlow.CatalogueService.domain.services.LiteratureClubQueryService;
import com.BookFlow.CatalogueService.interfaces.rest.resources.CreateLiteratureClubResource;
import com.BookFlow.CatalogueService.interfaces.rest.resources.LiteratureClubResource;
import com.BookFlow.CatalogueService.interfaces.rest.transform.CreateLiteratureClubCommandFromResourceAssembler;
import com.BookFlow.CatalogueService.interfaces.rest.transform.LiteratureClubResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/v1/literature_clubs", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Literature Club", description="Literature Clubs Management Endpoints")
public class LiteratureClubController {
    private final LiteratureClubQueryService literatureClubQueryService;
    private final LiteratureClubCommandService literatureClubCommandService;

    public LiteratureClubController(LiteratureClubQueryService literatureClubQueryService, LiteratureClubCommandService literatureClubCommandService){
        this.literatureClubQueryService = literatureClubQueryService;
        this.literatureClubCommandService = literatureClubCommandService;
    }

    @PostMapping
    public ResponseEntity<LiteratureClubResource> CreateLiteratureClub(@RequestBody CreateLiteratureClubResource resource) {
        var createLiteratureClubCommand = CreateLiteratureClubCommandFromResourceAssembler.toCommandFromResource(resource); // CreateLiteratureClubCommand
        var literatureClub = literatureClubCommandService.handle(createLiteratureClubCommand); //
        if (literatureClub.isEmpty()) return ResponseEntity.badRequest().build();
        var literatureClubResource = LiteratureClubResourceFromEntityAssembler.toResourceFromEntity(literatureClub.get());
        return new ResponseEntity<>(literatureClubResource, HttpStatus.CREATED);
    }

    @GetMapping("/clubId/{clubId}")
    public ResponseEntity<LiteratureClubResource> getLiteratureClubById(@PathVariable Long clubId) {
        var getLiteratureClubByIdQuery = new GetLiteratureClubByIdQuery(clubId);
        var literatureClub = literatureClubQueryService.handle(getLiteratureClubByIdQuery);
        if (literatureClub.isEmpty()) return ResponseEntity.badRequest().build();
        var literatureClubResource = LiteratureClubResourceFromEntityAssembler.toResourceFromEntity(literatureClub.get());
        return ResponseEntity.ok(literatureClubResource);
    }

    @GetMapping("/clubName/{name}")
    public ResponseEntity<LiteratureClubResource> getLiteratureClubByName(@PathVariable String name) {
        var getLiteratureClubByNameQuery = new GetLiteratureClubByNameQuery(name);
        var literatureClub = literatureClubQueryService.handle(getLiteratureClubByNameQuery);
        if (literatureClub.isEmpty()) return ResponseEntity.badRequest().build();
        var literatureClubResource = LiteratureClubResourceFromEntityAssembler.toResourceFromEntity(literatureClub.get());
        return ResponseEntity.ok(literatureClubResource);
    }

    @GetMapping
    public ResponseEntity<List<LiteratureClubResource>> getAllLiteratureClubs(){
        var getLiteratureClubsQuery = new GetAllLiteratureClubsQuery();
        var literatureClubs = literatureClubQueryService.handle(getLiteratureClubsQuery);
        var literatureClubResources = literatureClubs.stream().map(LiteratureClubResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(literatureClubResources);
    }
}
