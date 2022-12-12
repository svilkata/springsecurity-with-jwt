package com.example.autorepairsWithJWT.model.dto.userauth;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegisterRequestJsonBodyDTO {
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    private List<UserRoleUserRegisterJsonDTO> userRoles = new ArrayList<>();

    public UserRegisterRequestJsonBodyDTO() {
    }

    public String getEmail() {
        return email;
    }
    public UserRegisterRequestJsonBodyDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterRequestJsonBodyDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterRequestJsonBodyDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterRequestJsonBodyDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterRequestJsonBodyDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public List<UserRoleUserRegisterJsonDTO> getUserRoles() {
        return userRoles;
    }

    public UserRegisterRequestJsonBodyDTO setUserRoles(List<UserRoleUserRegisterJsonDTO> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    //    {
//        "id": 1,
//            "email": "admin@example.com",
//            "password": "c12e7899b988205982859d28adfd8c09f1dcfeba98b8d6e394c730bb9eaca25438625c379233f21a",
//            "firstName": "Admin",
//            "lastName": "Adminov",
//            "userRoles": [
//        {
//            "id": 1,
//                "userRole": "ADMIN"
//        },
//        {
//            "id": 2,
//                "userRole": "MODERATOR"
//        },
//        {
//            "id": 3,
//                "userRole": "USER"
//        }
//        ]
//    }
}

