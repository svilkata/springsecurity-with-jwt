package com.example.autorepairsWithJWT.web;

import com.example.autorepairsWithJWT.model.dto.userauth.UserRegisterRequestJsonBodyDTO;
import com.example.autorepairsWithJWT.model.entity.UserEntity;
import com.example.autorepairsWithJWT.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    //called on http://localhost:8080/users/register/1
    @GetMapping("/users/register/{userId}")
    public ResponseEntity<UserEntity> getOneUser(@PathVariable Long userId) {
        Optional<UserEntity> userEntityOpt = this.userService.findUserById(userId);
        return userEntityOpt.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(userEntityOpt.get());
    }

    //called on http://localhost:8000/users/register/all
    @GetMapping("/users/register/all")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> allUserEntitities = this.userService.findAllUsers();
        return ResponseEntity.ok(allUserEntitities);
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
//            "id":2,
//                "userRole":"MODERATOR"
//        },
//        {
//            "id":3,
//                "userRole":"USER"
//        }
//    ]
//    }
    @PostMapping("/users/register")
    public ResponseEntity<String> createUser(
            @RequestBody UserRegisterRequestJsonBodyDTO userRegisterRequestJsonBodyDTO,   //десериализация на body-то до Java обект – пропъртитата на боди-то на нашата заявка ще бъдат популирани върху нашето DTO
            UriComponentsBuilder builder) {
        System.out.println(userRegisterRequestJsonBodyDTO);

        Long userId = userService.registerNewUser(userRegisterRequestJsonBodyDTO);
        if (userId == -1L) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        if (userId == -2L) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        URI location = builder.path("/users/register/{id}")
                .buildAndExpand(userId)
                .toUri();

        return ResponseEntity
                .created(location)
                .body("User " + userRegisterRequestJsonBodyDTO.getUsername() + " has been registered successfully");
    }
}
