package com.secui.healthone.util;

import com.secui.healthone.domain.auth.entity.Role;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

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
                log.info("Auth Success!!");
                Authentication auth =getAuthentication(tokenService.getEmail(token), Role.MEMBER.toString());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }else{
                throw new JwtException("ACCESS TOKEN EXPIRED");
            }
        }catch(JwtException a){
            // token reissue
            String refreshtoken = cookieUtil.getRefreshTokenCookie(request);
            String email ="";
            String role = "";
            long userno = 0;
            try{
                email = tokenService.getEmail(refreshtoken);
                userno = (long) tokenService.getPayload(refreshtoken,"no");
                role = (String) tokenService.getPayload(refreshtoken,"role");
                log.info("email = {}, userno = {}, role = {}",email,userno,role);
                if(redisUtil.getData(email) == null || tokenService.getExpiredTokenClaims(refreshtoken)){
                    throw new JwtException("EXPIRED");
                }
            }catch(Exception j){
               log.error("REFRESH TOKEN EXPIRED");
               response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
            String accesstoken = tokenService.generateToken(email,userno,role,"ACCESS");
            String newrefreshtoken = tokenService.generateToken(email,userno, role,"REFRESH");
            ResponseCookie cookie = cookieUtil.getCookie(newrefreshtoken,tokenService.refreshPeriod);
            response.setContentType("application/json;charset=UTF-8");
            response.setHeader("Authorization",accesstoken);
            response.setHeader("Set-Cookie",cookie.toString());
            Authentication auth =getAuthentication(email,role);
            SecurityContextHolder.getContext().setAuthentication(auth);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }catch(Exception e){
            // Return UNAUTHORIZED
            log.error("BAD REQUEST");
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
        filterChain.doFilter(request, response);
    }


    public Authentication getAuthentication(String email, String role){
        log.info("Auth Email, Role = {} , {}",email,role);
        return new UsernamePasswordAuthenticationToken(email, "", Collections.singleton((new SimpleGrantedAuthority(role))));
    }

}
