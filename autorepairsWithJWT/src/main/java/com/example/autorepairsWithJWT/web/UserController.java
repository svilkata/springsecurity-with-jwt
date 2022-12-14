package com.example.autorepairsWithJWT.web;

import com.example.autorepairsWithJWT.model.dto.userregister.UserDtoResponse;
import com.example.autorepairsWithJWT.model.entity.RimEntity;
import com.example.autorepairsWithJWT.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //called on http://localhost:8080/users/1
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDtoResponse> getOneUser(@PathVariable Long userId) {
        return ResponseEntity.ok(this.userService.findUserById(userId));
    }

    //called on http://localhost:8000/users/all
    @GetMapping("/users/all")
    public ResponseEntity<List<UserDtoResponse>> getAllUsers() {
        return ResponseEntity.ok(this.userService.findAllUsers());
    }

    //calling DELETE on http://localhost:8000/users/4
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userId") Long userId) {
        this.userService.deleteUserById(userId);

        return ResponseEntity.noContent().build();
    }
}
