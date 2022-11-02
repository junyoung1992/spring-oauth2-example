package com.example.springoauth2.service;

import com.example.springoauth2.common.enums.AuthProvider;
import com.example.springoauth2.common.exception.BadRequestException;
import com.example.springoauth2.common.security.TokenProvider;
import com.example.springoauth2.controller.dto.request.SignUpReq;
import com.example.springoauth2.entity.User;
import com.example.springoauth2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public String getAuthenticationToken(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.createToken(authentication);
    }

    @Transactional
    public Long registerUser(SignUpReq signUpReq) {
        if (userRepository.existsByEmail(signUpReq.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        User user = User.localRegisterBuilder()
                .name(signUpReq.getName())
                .email(signUpReq.getEmail())
                .password(passwordEncoder.encode(signUpReq.getPassword()))
                .provider(AuthProvider.local)
                .build();

        User result = userRepository.save(user);

        return result.getId();
    }

}
