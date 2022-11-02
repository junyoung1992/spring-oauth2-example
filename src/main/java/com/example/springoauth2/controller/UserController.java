package com.example.springoauth2.controller;

import com.example.springoauth2.common.security.CurrentUser;
import com.example.springoauth2.common.security.UserPrincipal;
import com.example.springoauth2.controller.dto.response.UserRes;
import com.example.springoauth2.entity.User;
import com.example.springoauth2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserRes getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        User result = userService.findByUserId(userPrincipal.getId());
        return UserRes.fromEntity(result);
    }

}
