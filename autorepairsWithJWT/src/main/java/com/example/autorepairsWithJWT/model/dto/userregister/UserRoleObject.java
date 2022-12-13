package com.example.autorepairsWithJWT.model.dto.userregister;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoleObject {
    private String userRole;

    public UserRoleObject() {
    }

    public String getUserRole() {
        return userRole;
    }

    public UserRoleObject setUserRole(String userRole) {
        this.userRole = userRole;
        return this;
    }
}
