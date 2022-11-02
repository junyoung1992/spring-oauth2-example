package com.example.springoauth2.controller.dto.response;

import lombok.Getter;

@Getter
public class AuthRes {

    private final String accessToken;
    private final String tokenType = "Bearer";

    public AuthRes(String accessToken) {
        this.accessToken = accessToken;
    }

}
