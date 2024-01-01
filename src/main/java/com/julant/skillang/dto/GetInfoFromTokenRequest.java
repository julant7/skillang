package com.julant.skillang.dto;

public class GetInfoFromTokenRequest {
    private String token;

    public GetInfoFromTokenRequest() {

    }

    public GetInfoFromTokenRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
