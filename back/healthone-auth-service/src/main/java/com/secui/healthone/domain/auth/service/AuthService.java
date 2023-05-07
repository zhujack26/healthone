package com.secui.healthone.domain.auth.service;


import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.secui.healthone.domain.auth.config.KeyPairConfig;
import com.secui.healthone.domain.auth.entity.Role;
import com.secui.healthone.domain.auth.entity.User;
import com.secui.healthone.domain.auth.repository.UserRepository;
import com.secui.healthone.global.config.security.jwt.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;


    private final UserRepository userRepository;

    private final TokenService tokenService;

    private final KeyPairConfig keyPairConfig;

    public Map<String, String> isSignUp(String authCode) throws IOException {
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
        String userId = payload.getEmail();
        log.info("EMail= {}", userId);
        // 회원가입이 되있는지 확인
//        if (true) {
//            // 회원가입이 안되 있을 경우 회원가입
//        }
        // 회원가입이 되있을 경우 토큰 생성 후 return
        KeyPair keyPair = keyPairConfig.keyPair();
        // OpenSSL을 통해서 비대칭키 세트를 발급받아서 그 키로 claim을 만들어야함
//        PublicKey publicKey = keyPair.getPublic();
//        PrivateKey privateKey = keyPair.getPrivate();
        String refreshtoken = "";
        String accesstoken = "";
        try {
            refreshtoken = tokenService.generateToken(userId, Role.MEMBER.toString(), "REFRESH", privateKey);
            accesstoken = tokenService.generateToken(userId, Role.MEMBER.toString(), "ACCESS", privateKey);
        }catch (Exception e){
            e.getStackTrace();
        }
        log.info("Refresh Token = {},Access Token = {}", refreshtoken, accesstoken);
        Map<String, String> token = new HashMap<>();
        token.put("refreshtoken", refreshtoken);
        token.put("accesstoken", accesstoken);
        return token;
    }
}

