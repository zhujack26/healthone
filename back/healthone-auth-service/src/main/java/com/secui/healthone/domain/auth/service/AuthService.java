package com.secui.healthone.domain.auth.service;


import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.secui.healthone.domain.auth.entity.Role;
import com.secui.healthone.domain.auth.entity.User;
import com.secui.healthone.domain.auth.repository.RedisRepository;
import com.secui.healthone.domain.auth.repository.UserRepository;
import com.secui.healthone.util.RedisUtil;
import com.secui.healthone.util.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final RedisUtil redisUtil;
    private final RedisRepository redisRepository;

    public Map<String, String> isSignUp(String authCode) throws IOException {
        // Exchange auth code for access token
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        GoogleAuthorizationCodeTokenRequest tokenRequest = new GoogleAuthorizationCodeTokenRequest(
                new NetHttpTransport(),
                jsonFactory,
                "https://oauth2.googleapis.com/token",
                "257241734993-6fhqboc805rprdme7eu0vusie9foioal.apps.googleusercontent.com",
                "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",
                URLDecoder.decode(authCode),
                "https://back.apihealthone.com" // This should match the redirect_uri in your Android app
        );
        GoogleTokenResponse tokenResponse = tokenRequest.execute();
        GoogleIdToken idToken = tokenResponse.parseIdToken();
        GoogleIdToken.Payload payload = idToken.getPayload();
        String email = payload.getEmail();
        log.info("EMail= {}", email);
        // 회원가입이 되있는지 확인
        if (userRepository.findByEmail(email).isEmpty()) {
            userRepository.save(
                    User.builder()
                            .email(email)
                            .role(Role.MEMBER)
                            .name("temp")
                            .build());
            // 회원가입이 안되 있을 경우 회원가입
        }
        long userno = userRepository.findByEmail(email).get().getId();
        log.info("userno = {}",userno);
        // 회원가입이 되있을 경우 토큰 생성 후 return
        String refreshtoken = tokenService.generateToken(email,userno, Role.MEMBER.toString(), "REFRESH");
        String accesstoken = tokenService.generateToken(email,userno, Role.MEMBER.toString(), "ACCESS");
        redisUtil.setDataExpire(email,refreshtoken,tokenService.refreshPeriod);
        log.info("Refresh Token = {},Access Token = {}", refreshtoken, accesstoken);
        Map<String, String> token = new HashMap<>();
        token.put("refreshtoken", refreshtoken);
        token.put("accesstoken", accesstoken);
        return token;
    }


    public boolean logout(String accesstoken,String refreshtoken) throws Exception{
        redisRepository.deleteById(refreshtoken);
        redisUtil.setDataExpire(tokenService.getEmail(accesstoken),accesstoken,tokenService.accessPeriod);
        return true;
    }
}

