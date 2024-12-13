package com.BookFlow.usuarios.iam.interfaces.rest.transform;

import com.BookFlow.usuarios.iam.domain.model.commands.SignInCommand;
import com.BookFlow.usuarios.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource resource) {
        return new SignInCommand(resource.username(), resource.password());
    }
}
