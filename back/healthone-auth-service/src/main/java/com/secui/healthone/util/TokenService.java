package com.secui.healthone.util;


import com.google.api.client.util.Value;
import com.secui.healthone.domain.auth.entity.Role;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenService {

//    @Value("${springboot.jwt.secret}")
    private String secretKey = "tset";
    public static long accessPeriod = 1000L * 60L * 60L * 24L * 30L;
    public static long refreshPeriod = 1000L * 60L * 60L * 24L * 30L;


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String generateToken(String email,long userno, String role, String tokentype){
        Claims claims = Jwts.claims()
                .setSubject(email);
        claims.put("no",userno);
        claims.put("role", role);
        Date now = new Date();
        log.info("Expire Time = {}",new Date(now.getTime()+ (tokentype.equals("ACCESS") ? accessPeriod : refreshPeriod)));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(now.getTime() + (tokentype.equals("ACCESS") ? accessPeriod : refreshPeriod)))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return claims.getBody()
                    .getExpiration()
                    .after(new Date());
        }catch(Exception e){
            e.printStackTrace();
            log.error("Access Token Expired");
        }
        return false;
    }

    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public Object getPayload(String token, String className) {
        return (Object) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get(className,String.class);
    }

    public boolean getExpiredTokenClaims(String token) throws ExpiredJwtException{
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Refresh token");
            return true;
        }
        return false;
    }


}

