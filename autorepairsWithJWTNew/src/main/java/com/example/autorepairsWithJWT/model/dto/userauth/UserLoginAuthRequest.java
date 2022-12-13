package com.example.autorepairsWithJWT.model.dto.userauth;

public class UserLoginAuthRequest {
    private String username;
    private String password;

    public UserLoginAuthRequest() {
    }

    public UserLoginAuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public UserLoginAuthRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginAuthRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
