package com.secui.healthone.domain.auth.service;

import com.secui.healthone.domain.auth.dto.SignUpRequestDto;

import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    boolean isSignUp(String email);

    void signUp(SignUpRequestDto signUpRequestDto);

    void signIn(HttpServletResponse response, String email);
}
