package com.example.autorepairsWithJWT.web;

import com.example.autorepairsWithJWT.model.dto.userregister.UserRegisterRequest;
import com.example.autorepairsWithJWT.model.entity.UserEntity;
import com.example.autorepairsWithJWT.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    //called on http://localhost:8000/users/register
//    {
//        "email":"mmmmmmmm@example.com",
//            "username":"mmmmmmoderator",
//            "password":"topsecrect",
//            "firstName":"Mmmmmmoderator",
//            "lastName":"Mmmmmmmoderatorov",
//            "userRoles": [
//        {
//                "userRole":"MODERATOR"
//        },
//        {
//                "userRole":"USER"
//        }
//    ]
//    }
    @PostMapping("/users/register")
    public ResponseEntity<?> createUser(
            @RequestBody UserRegisterRequest userRegisterRequest, UriComponentsBuilder builder) {

        UserEntity createdNewUser = userService.registerNewUser(userRegisterRequest);

        URI location = builder.path("/users/register/{id}")
                .buildAndExpand(createdNewUser.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body("User: %s with email: %s has been registered successfully"
                        .formatted(createdNewUser.getUsername(), createdNewUser.getEmail()));
    }
}
