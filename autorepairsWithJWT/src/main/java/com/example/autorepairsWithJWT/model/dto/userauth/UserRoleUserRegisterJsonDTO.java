package com.example.autorepairsWithJWT.model.dto.userauth;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoleUserRegisterJsonDTO {
    private String id;
    private String userRole;

    public UserRoleUserRegisterJsonDTO() {
    }

    public String getId() {
        return id;
    }

    public UserRoleUserRegisterJsonDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserRole() {
        return userRole;
    }

    public UserRoleUserRegisterJsonDTO setUserRole(String userRole) {
        this.userRole = userRole;
        return this;
    }
}
