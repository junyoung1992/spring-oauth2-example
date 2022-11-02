package com.example.springoauth2.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiRes {

    private final boolean success;
    private final String message;

}
