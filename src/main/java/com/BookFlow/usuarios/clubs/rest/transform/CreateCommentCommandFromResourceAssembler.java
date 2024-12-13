package com.BookFlow.usuarios.clubs.rest.transform;

import com.BookFlow.usuarios.clubs.domain.model.commands.CreateCommentCommand;
import com.BookFlow.usuarios.clubs.rest.resources.CreateCommentResource;

public class CreateCommentCommandFromResourceAssembler {
    public static CreateCommentCommand toCommandFromResource(CreateCommentResource resource) {
        return new CreateCommentCommand(
                resource.content()
        );
    }
}
