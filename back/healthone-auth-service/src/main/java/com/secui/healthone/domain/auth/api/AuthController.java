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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.net.URLDecoder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @GetMapping
    public ResponseEntity test(){
        return ResponseEntity.ok().body("hello");
    }
    @PostMapping("/login")
    public void signInOrUp(@RequestBody String authCode) throws Exception{
        log.info("authCode = {} ",authCode);
        log.info("들어왔습니다");
        // Exchange auth code for access token
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        GoogleAuthorizationCodeTokenRequest tokenRequest = new GoogleAuthorizationCodeTokenRequest(
                new NetHttpTransport(),
                jsonFactory,
                "https://oauth2.googleapis.com/token",
                "785216230516-1h8hoagkginolldjirvtgiebidmqe38i.apps.googleusercontent.com",
                "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",
                URLDecoder.decode(authCode),
                "http://localhost" // This should match the redirect_uri in your Android app
        );
        GoogleTokenResponse tokenResponse = tokenRequest.execute();
        GoogleIdToken idToken = tokenResponse.parseIdToken();
        GoogleIdToken.Payload payload = idToken.getPayload();
        String userId = payload.getSubject();
        log.info("IDTOKEN = {}",userId);
        log.info("EMail= {}",payload.getEmail());

    }
}
