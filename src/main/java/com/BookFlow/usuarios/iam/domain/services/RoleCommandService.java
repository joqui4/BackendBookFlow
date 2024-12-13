package com.BookFlow.usuarios.iam.domain.services;

import com.BookFlow.usuarios.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
