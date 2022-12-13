package com.example.autorepairsWithJWT.model.dto.userregister;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegisterRequest {
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    private List<UserRoleObject> userRoles = new ArrayList<>();

    public UserRegisterRequest() {
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterRequest setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterRequest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public List<UserRoleObject> getUserRoles() {
        return userRoles;
    }

    public UserRegisterRequest setUserRoles(List<UserRoleObject> userRoles) {
        this.userRoles = userRoles;
        return this;
    }
}

