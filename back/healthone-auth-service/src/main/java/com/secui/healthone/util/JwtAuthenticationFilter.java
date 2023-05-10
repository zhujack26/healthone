package com.secui.healthone.util;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final CookieUtil cookieUtil;
    private final RedisUtil redisUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = HeaderUtil.getAccessToken(request);
        log.info("Filter token = {}", token);
        try {
            if (token != null && tokenService.verifyToken(token)) {
                if (redisUtil.getData(token) != null){
                    throw new JwtException("BLACKLIST ACCESSS TOKEN");
                }
            }else{
                throw new JwtException("ACCESS TOKEN EXPIRED");
            }
        }catch(JwtException e){
            // token reissue
            String refreshtoken = cookieUtil.getRefreshTokenCookie(request);
            String email ="";
            String role = "";
            try{
                email = tokenService.getEmail(refreshtoken);
                role = tokenService.getPayload(refreshtoken,"role");
                if(redisUtil.getData(email) == null || tokenService.getExpiredTokenClaims(refreshtoken)){
                    throw new JwtException("EXPIRED");
                }
            }catch(Exception j){
               log.error("REFRESH TOKEN EXPIRED");
               response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
            String accesstoken = tokenService.generateToken(email,role,"ACCESS");
            String newrefreshtoken = tokenService.generateToken(email,role,"REFRESH");
            ResponseCookie cookie = cookieUtil.getCookie(newrefreshtoken,tokenService.refreshPeriod);
            response.setContentType("application/json;charset=UTF-8");
            response.setHeader("Authorization","Bearer " + accesstoken);
            response.setHeader("Set-Cookie",cookie.toString());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }catch(Exception e){
            // Return UNAUTHORIZED
            log.error("BAD REQUEST");
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
        filterChain.doFilter(request, response);
    }
}
