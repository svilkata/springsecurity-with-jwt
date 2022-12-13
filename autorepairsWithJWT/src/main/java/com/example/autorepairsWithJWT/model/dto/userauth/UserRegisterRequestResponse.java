package com.example.autorepairsWithJWT.model.dto.userauth;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegisterRequestResponse {
    private Long id;
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    private List<UserRoleObject> userRoles = new ArrayList<>();

    public UserRegisterRequestResponse() {
    }

    public Long getId() {
        return id;
    }

    public UserRegisterRequestResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterRequestResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterRequestResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterRequestResponse setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterRequestResponse setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterRequestResponse setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public List<UserRoleObject> getUserRoles() {
        return userRoles;
    }

    public UserRegisterRequestResponse setUserRoles(List<UserRoleObject> userRoles) {
        this.userRoles = userRoles;
        return this;
    }
}

