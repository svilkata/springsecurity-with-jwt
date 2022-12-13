package com.example.autorepairsWithJWT.web;

import com.example.autorepairsWithJWT.model.dto.userauth.UserLoginAuthRequest;
import com.example.autorepairsWithJWT.model.dto.userauth.UserLoginAuthResponse;
import com.example.autorepairsWithJWT.service.AppUserDetailsService;
import com.example.autorepairsWithJWT.utils.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final AppUserDetailsService appUserDetailsService;
    private final AuthenticationManager authManager;
    private final JwtTokenUtil jwtUtil;

    public LoginController(AppUserDetailsService appUserDetailsService, AuthenticationManager authManager, JwtTokenUtil jwtUtil) {
        this.appUserDetailsService = appUserDetailsService;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    //calling POST on http://localhost:8000/users/login
//    {
//        "email": "user@example.com",
//            "username": "Svilen",
//            "password": "topsecrect"
//    }
    @PostMapping("/users/login")
    public ResponseEntity<?> authenticateUserLogin(@RequestBody UserLoginAuthRequest userLoginAuthRequest) {
        try {
            UserDetails userDetails =
                    appUserDetailsService.loadUserByUsername(userLoginAuthRequest.getUsername());

            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            userLoginAuthRequest.getPassword(),
                            userDetails.getAuthorities())
            );

            String accessToken = jwtUtil.generateAccessToken(userDetails);

            return ResponseEntity.ok().body(new UserLoginAuthResponse(userLoginAuthRequest.getUsername(), accessToken));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
