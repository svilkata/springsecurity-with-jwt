package com.example.autorepairsWithJWT.model.dto.userauth;

public class UserLoginAuthResponse {
    private String username;
    private String accessToken;

    public UserLoginAuthResponse() {
    }

    public UserLoginAuthResponse(String username, String accessToken) {
        this.username = username;
        this.accessToken = accessToken;
    }

    public String getUsername() {
        return username;
    }

    public UserLoginAuthResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
