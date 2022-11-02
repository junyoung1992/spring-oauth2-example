package com.example.springoauth2.controller;

import com.example.springoauth2.controller.dto.request.LoginReq;
import com.example.springoauth2.controller.dto.request.SignUpReq;
import com.example.springoauth2.controller.dto.response.ApiRes;
import com.example.springoauth2.controller.dto.response.AuthRes;
import com.example.springoauth2.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthRes> authenticateUser(@Valid @RequestBody LoginReq loginReq) {
        String token = authService.getAuthenticationToken(loginReq.getEmail(), loginReq.getPassword());
        return ResponseEntity.ok(new AuthRes(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiRes> registerUser(@Valid @RequestBody SignUpReq signUpReq) {
        Long userId = authService.registerUser(signUpReq);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/user/me")
                .buildAndExpand(userId)
                .toUri();

        return ResponseEntity.created(location)
                .body(new ApiRes(true, "User registered successfully."));
    }

}
