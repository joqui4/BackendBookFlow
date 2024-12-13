package com.BookFlow.usuarios.iam.interfaces.rest.transform;

import com.BookFlow.usuarios.iam.domain.model.entities.Role;
import com.BookFlow.usuarios.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role entity) {
        return new RoleResource(entity.getId(), entity.getStringName());

    }
}
