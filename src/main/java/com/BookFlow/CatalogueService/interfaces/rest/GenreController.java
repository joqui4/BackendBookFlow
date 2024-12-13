package com.BookFlow.CatalogueService.interfaces.rest;

import com.BookFlow.CatalogueService.domain.model.queries.*;
import com.BookFlow.CatalogueService.domain.services.GenreCommandService;
import com.BookFlow.CatalogueService.domain.services.GenreQueryService;
import com.BookFlow.CatalogueService.interfaces.rest.resources.CreateGenreResource;
import com.BookFlow.CatalogueService.interfaces.rest.resources.GenreResource;
import com.BookFlow.CatalogueService.interfaces.rest.transform.CreateGenreCommandFromResourceAssembler;
import com.BookFlow.CatalogueService.interfaces.rest.transform.GenreResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/v1/genres", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Genre", description="Genres Management Endpoints")
public class GenreController {
    private final GenreQueryService genreQueryService;
    private final GenreCommandService genreCommandService;

    public GenreController(GenreQueryService genreQueryService, GenreCommandService genreCommandService){
        this.genreQueryService = genreQueryService;
        this.genreCommandService = genreCommandService;
    }

    @PostMapping
    public ResponseEntity<GenreResource> CreateGenre(@RequestBody CreateGenreResource resource) {
        var createGenreCommand = CreateGenreCommandFromResourceAssembler.toCommandFromResource(resource); // CreateCategoryCommand
        var genre = genreCommandService.handle(createGenreCommand); //
        if (genre.isEmpty()) return ResponseEntity.badRequest().build();
        var genreResource = GenreResourceFromEntityAssembler.toResourceFromEntity(genre.get());
        return new ResponseEntity<>(genreResource, HttpStatus.CREATED);
    }
    @GetMapping("/genreId/{genreId}")
    public ResponseEntity<GenreResource> getGenreById(@PathVariable Long genreId) {
        var getGenreByIdQuery = new GetGenreByIdQuery(genreId);
        var genre = genreQueryService.handle(getGenreByIdQuery);
        if (genre.isEmpty()) return ResponseEntity.badRequest().build();
        var genreResource = GenreResourceFromEntityAssembler.toResourceFromEntity(genre.get());
        return ResponseEntity.ok(genreResource);
    }
    @GetMapping("/genreName")
    public ResponseEntity<GenreResource> getGenreByName(@RequestParam String name) {
        var getGenreByNameQuery = new GetGenreByNameQuery(name);
        var genre = genreQueryService.handle(getGenreByNameQuery);
        if (genre.isEmpty()) return ResponseEntity.badRequest().build();
        var genreResource = GenreResourceFromEntityAssembler.toResourceFromEntity(genre.get());
        return ResponseEntity.ok(genreResource);
    }
    @GetMapping
    public ResponseEntity<List<GenreResource>> getAllGenres(){
        var getGenresQuery = new GetAllGenresQuery();
        var genres = genreQueryService.handle(getGenresQuery);
        var genreResource = genres.stream().map(GenreResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(genreResource);
    }
}
