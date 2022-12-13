package com.example.autorepairsWithJWT.model.dto.userregister;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDtoResponse {
    private Long id;
    private String email;
    private String username;
    private String fullname;

    private List<UserRoleObject> userRoles = new ArrayList<>();

    public UserDtoResponse() {
    }

    public Long getId() {
        return id;
    }

    public UserDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDtoResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDtoResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<UserRoleObject> getUserRoles() {
        return userRoles;
    }

    public UserDtoResponse setUserRoles(List<UserRoleObject> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    public String getFullname() {
        return fullname;
    }

    public UserDtoResponse setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }
}

