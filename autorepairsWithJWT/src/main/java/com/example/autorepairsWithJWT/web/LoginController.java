package com.example.autorepairsWithJWT.web;

import com.example.autorepairsWithJWT.config.JwtTokenUtil;
import com.example.autorepairsWithJWT.model.dto.userauth.UserLoginAuthRequest;
import com.example.autorepairsWithJWT.model.dto.userauth.UserLoginAuthResponse;
import com.example.autorepairsWithJWT.model.entity.UserEntity;
import com.example.autorepairsWithJWT.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {
    private final UserDetailsService appUserDetailsService;
    private final UserService userService;

    private final AuthenticationManager authManager;
    private final JwtTokenUtil jwtUtil;

    public LoginController(UserDetailsService appUserDetailsService, UserService userService, AuthenticationManager authManager, JwtTokenUtil jwtUtil) {
        this.appUserDetailsService = appUserDetailsService;
        this.userService = userService;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    //calling POST on http://localhost:8000/users/login
//    {
//        "email": "user@example.com",
//            "username": "Svilen",
//            "password": "topsecrect",
//            "firstName": "User",
//            "lastName": "Userov",
//            "userRoles": [
//        {
//            "id": 3,
//                "userRole": "USER"
//        }
//    ]
//    }

//    {
//        "email": "user@example.com",
//            "username": "Svilen",
//            "password": "topsecrect"
//    }
    @PostMapping("/users/login")
    public ResponseEntity<?> authenticateUserLogin(@RequestBody UserLoginAuthRequest userLoginAuthRequest) {
        Optional<UserEntity> userDBExists = this.userService.findUserByUsernameAndPassword(
                userLoginAuthRequest.getUsername(), userLoginAuthRequest.getPassword());

        if (userDBExists.isEmpty()) {
            return new ResponseEntity<>(
                    "Username and password do not match",
                    HttpStatus.CONFLICT);
        }

        try {
            UserDetails userDetails =
                    appUserDetailsService.loadUserByUsername(userDBExists.get().getUsername());

            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            userLoginAuthRequest.getPassword(),
                            userDetails.getAuthorities())
            );

            UserEntity user = (UserEntity) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            UserLoginAuthResponse response = new UserLoginAuthResponse(user.getEmail(), accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
