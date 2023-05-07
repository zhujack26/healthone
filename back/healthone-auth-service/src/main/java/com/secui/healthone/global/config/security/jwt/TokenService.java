package com.secui.healthone.global.config.security.jwt;


import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
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
@Service
@RequiredArgsConstructor
public class TokenService {

    private String secretKey = "token-secret-key";
    public static long accessPeriod = 1000L * 60L * 60L * 24L * 2L;
    public static long refreshPeriod = 1000L * 60L * 60L * 24L * 30L;


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String generateToken(String email, String role, String tokentype,PrivateKey privateKey) throws JOSEException {
        JWSSigner signer = new RSASSASigner(privateKey);
        Date now = new Date();
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("ahg0824@gmail.com")
                .expirationTime(new Date(now.getTime()+ (tokentype.equals("ACCESS") ? accessPeriod : refreshPeriod)))
                .build();
        SignedJWT signedJWT = new SignedJWT((new JWSHeader(JWSAlgorithm.RS256)),claimsSet);
        signedJWT.sign(signer);
        log.info("Expire Time = {}",new Date(now.getTime()+ (tokentype.equals("ACCESS") ? accessPeriod : refreshPeriod)));
        return signedJWT.serialize();
    }

    public static boolean verifyJwt(String token, String publicKey) throws ParseException,JOSEException {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) publicKey);
            return signedJWT.verify(verifier);
        } catch (ParseException | JOSEException e) {
            return false;
        }
    }
//
//    public String getEmail(String token) {
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
//    }
//
//    public String getPayload(String token, String className) {
//        return (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get(className,String.class);
//    }
//
//    public boolean getExpiredTokenClaims(String token) throws ExpiredJwtException{
//        try {
//            Jwts.parser()
//                    .setSigningKey(secretKey)
//                    .parseClaimsJws(token)
//                    .getBody();
//        } catch (ExpiredJwtException e) {
//            log.info("Expired JWT Refresh token");
//            return true;
//        }
//        return false;
//    }
}

