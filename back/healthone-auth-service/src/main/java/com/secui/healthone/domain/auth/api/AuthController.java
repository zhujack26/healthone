package com.secui.healthone.domain.auth.api;

//import com.secui.healthone.domain.auth.dto.SignUpRequestDto;
//import com.secui.healthone.domain.auth.service.AuthService;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.secui.healthone.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> signInOrUp(@RequestBody String authCode) throws Exception{
        log.info("authCode = {} ",authCode);
        try {
            Map<String, String> token = authService.isSignUp(authCode);
            log.info("Token generated = {} ",token.entrySet());
            return ResponseEntity.ok().body(token);
        }catch (Exception e){
            e.getStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 토큰 검증


    // 토큰 재발급

}
