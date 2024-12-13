package com.BookFlow.usuarios.clubs.rest;

import com.BookFlow.usuarios.clubs.domain.model.queries.GetAllCommentsQuery;
import com.BookFlow.usuarios.clubs.domain.model.queries.GetCommentByIdQuery;
import com.BookFlow.usuarios.clubs.domain.model.queries.GetCommentByNameQuery;
import com.BookFlow.usuarios.clubs.domain.services.CommentCommandService;
import com.BookFlow.usuarios.clubs.domain.services.CommentQueryService;
import com.BookFlow.usuarios.clubs.rest.resources.CommentResource;
import com.BookFlow.usuarios.clubs.rest.resources.CreateCommentResource;
import com.BookFlow.usuarios.clubs.rest.transform.CommentResourceFromEntityAssembler;
import com.BookFlow.usuarios.clubs.rest.transform.CreateCommentCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/v1/comments", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Comment", description="Comments Management Endpoints")
public class CommentController {
    private final CommentQueryService commentQueryService;
    private final CommentCommandService commentCommandService;

    public CommentController(CommentQueryService commentQueryService, CommentCommandService commentCommandService){
        this.commentQueryService = commentQueryService;
        this.commentCommandService = commentCommandService;
    }

    @PostMapping
    public ResponseEntity<CommentResource> CreateComment(@RequestBody CreateCommentResource resource) {
        var createCommentCommand = CreateCommentCommandFromResourceAssembler.toCommandFromResource(resource);
        var comment = commentCommandService.handle(createCommentCommand);
        if (comment.isEmpty()) return ResponseEntity.badRequest().build();
        var commentResource = CommentResourceFromEntityAssembler.toResourceFromEntity(comment.get());
        return new ResponseEntity<>(commentResource, HttpStatus.CREATED);
    }

    @GetMapping("/commentId/{commentId}")
    public ResponseEntity<CommentResource> getCommentById(@PathVariable Long commentId) {
        var getCommentByIdQuery = new GetCommentByIdQuery(commentId);
        var comment = commentQueryService.handle(getCommentByIdQuery);
        if (comment.isEmpty()) return ResponseEntity.badRequest().build();
        var commentResource = CommentResourceFromEntityAssembler.toResourceFromEntity(comment.get());
        return ResponseEntity.ok(commentResource);
    }

    @GetMapping("/commentContent/{content}")
    public ResponseEntity<CommentResource> getCommentByName(@PathVariable String content) {
        var getCommentByNameQuery = new GetCommentByNameQuery(content);
        var comment = commentQueryService.handle(getCommentByNameQuery);
        if (comment.isEmpty()) return ResponseEntity.badRequest().build();
        var commentResource = CommentResourceFromEntityAssembler.toResourceFromEntity(comment.get());
        return ResponseEntity.ok(commentResource);
    }

    @GetMapping
    public ResponseEntity<List<CommentResource>> getAllComments(){
        var getCommentsQuery = new GetAllCommentsQuery();
        var comments = commentQueryService.handle(getCommentsQuery);
        var commentResources = comments.stream().map(CommentResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(commentResources);
    }
}
