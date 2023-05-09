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
import com.secui.healthone.CookieUtil;
import com.secui.healthone.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    private final CookieUtil cookieUtil;

    @PostMapping("/login")
    public ResponseEntity signInOrUp(@RequestBody String authCode){
        log.info("authCode = {} ",authCode);
        try {
            Map<String, String> token = authService.isSignUp(authCode);
            log.info("Token generated = {} ",token.entrySet());
//            response.addHeader("Authorization",token.get("accesstoken"));
//            response.setHeader("Set-Cookie",cookieUtil.getCookie(token.get("refreshtoken"),1000L * 60L * 60L * 24L * 30L));
            return ResponseEntity.ok()
                    .header(SET_COOKIE,cookieUtil.getCookie(token.get("refreshtoken"),1000L * 60L * 60L * 24L * 30L).toString())
                    .header("Authorization",token.get("accesstoken"))
                    .build();
        }catch (IOException e){
            e.getStackTrace();
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // 토큰 검증


    // 토큰 재발급

}
