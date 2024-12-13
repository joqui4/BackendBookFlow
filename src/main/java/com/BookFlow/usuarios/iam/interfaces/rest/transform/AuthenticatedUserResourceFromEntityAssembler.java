package com.BookFlow.usuarios.iam.interfaces.rest.transform;

import com.BookFlow.usuarios.iam.domain.model.aggregates.User;
import com.BookFlow.usuarios.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User entity, String token) {
        return new AuthenticatedUserResource(entity.getId(), entity.getUsername(), token);
    }
}
