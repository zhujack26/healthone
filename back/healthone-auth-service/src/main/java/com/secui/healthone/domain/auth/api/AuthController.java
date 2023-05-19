package com.secui.healthone.domain.auth.api;

import com.secui.healthone.util.CookieUtil;
import com.secui.healthone.domain.auth.service.AuthService;
import com.secui.healthone.util.HeaderUtil;
import com.secui.healthone.util.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final CookieUtil cookieUtil;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> signInOrUp(@RequestBody String authCode){
        log.info("authCode = {} ",authCode);
        try {
            Map<String, String> token = authService.isSignUp(authCode);
            log.info("Token generated = {} ",token.entrySet());
            return ResponseEntity.ok()
                    .header(SET_COOKIE,cookieUtil.getCookie(token.get("refreshtoken"),tokenService.refreshPeriod).toString())
                    .header("Authorization",token.get("accesstoken"))
                    .build();
        }catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // 토큰 검증
//    @PostMapping("/verify")
//    public ResponseEntity<?> verifyToken(@RequestHeader("Authorization") String Authorization){
//        log.info("Authorization = {}",Authorization);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization")String Authorization, HttpServletRequest request){
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        String refreshToken = cookieUtil.getRefreshTokenCookie(request);
        return ResponseEntity.ok().build();
    }
}
