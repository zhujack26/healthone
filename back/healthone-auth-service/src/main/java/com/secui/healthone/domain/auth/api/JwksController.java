//package com.secui.healthone.domain.auth.api;
//
//import com.nimbusds.jose.jwk.JWKSet;
//import com.nimbusds.jose.jwk.RSAKey;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.security.KeyPair;
//import java.security.interfaces.RSAPublicKey;
//
//@RestController
//@RequestMapping("/.well-known")
//public class JwksController {
//
//    private final KeyPair keyPair;
//
//    public JwksController(KeyPair keyPair) {
//        this.keyPair = keyPair;
//    }
//
//    @GetMapping("/jwks.json")
//    public ResponseEntity<JWKSet> getJwks() {
//        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//        RSAKey rsaKey = new RSAKey.Builder(publicKey).build();
//        JWKSet jwkSet = new JWKSet(rsaKey);
//
//        return ResponseEntity.ok(jwkSet);
//    }
//}