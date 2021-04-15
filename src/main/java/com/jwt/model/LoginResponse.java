package com.jwt.model;

public class LoginResponse {
    private String token;
    private Long expiry;

    public LoginResponse(String token, Long expiry) {
        this.token = token;
        this.expiry = expiry;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpiry() {
        return expiry;
    }

    public void setExpiry(Long expiry) {
        this.expiry = expiry;
    }
}
