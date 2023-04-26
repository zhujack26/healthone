package com.secui.healthone.domain.auth.api;

import com.secui.healthone.domain.auth.dto.SignUpRequestDto;
import com.secui.healthone.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/")
    public void signInOrUp(HttpServletResponse response, OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        String email = (String) oAuth2AuthenticationToken.getPrincipal().getAttributes().get("email");
        System.out.println("email: " + email);
        boolean isSignUp = authService.isSignUp(email);
        if (isSignUp) {
            this.needsSignUp(email);
        } else {
            this.signIn(response, email);
        }
        return;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        authService.signUp(signUpRequestDto);
    }

    private String needsSignUp(String email) {
        return email;
    }

    private void signIn(HttpServletResponse response, String email) {
        authService.signIn(response, email);
    }


}
