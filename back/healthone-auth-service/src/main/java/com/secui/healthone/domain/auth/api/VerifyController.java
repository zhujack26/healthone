package com.secui.healthone.domain.auth.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class VerifyController {

    @PostMapping
    public ResponseEntity<?> verifyToken(@RequestHeader("Authorization") String Authorization){
        log.info("Authorization = {}",Authorization);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/meal/test")
    public ResponseEntity<?> verifyToken(@RequestHeader("Authorization") String Authorization){
        log.info("Authorization = {}",Authorization);
        return ResponseEntity.ok().build();
    }
}
