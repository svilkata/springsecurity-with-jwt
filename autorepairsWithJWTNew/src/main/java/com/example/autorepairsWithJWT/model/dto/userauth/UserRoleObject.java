package com.example.autorepairsWithJWT.model.dto.userauth;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoleObject {
    private String id;
    private String userRole;

    public UserRoleObject() {
    }

    public String getId() {
        return id;
    }

    public UserRoleObject setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserRole() {
        return userRole;
    }

    public UserRoleObject setUserRole(String userRole) {
        this.userRole = userRole;
        return this;
    }
}
