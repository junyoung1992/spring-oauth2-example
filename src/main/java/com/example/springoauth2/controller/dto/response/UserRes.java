package com.example.springoauth2.controller.dto.response;

import com.example.springoauth2.entity.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserRes {

    private final String email;
    private final String name;
    private final String imageUrl;

    public static UserRes fromEntity(User entity) {
        return new UserRes(entity.getEmail(), entity.getName(), entity.getImageUrl());
    }

}
