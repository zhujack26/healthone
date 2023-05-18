package com.secui.healthone.domain.auth.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class VerifyController {

    @PatchMapping("**")
    public ResponseEntity<?> verifyToken5(@RequestHeader("Authorization") String Authorization){
        log.info("Authorization = {}",Authorization);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("**")
    public ResponseEntity<?> verifyToken(@RequestHeader("Authorization") String Authorization){
        log.info("Authorization = {}",Authorization);
        return ResponseEntity.ok().build();
    }

    @GetMapping("**")
    public ResponseEntity<?> verifyToken2(@RequestHeader("Authorization") String Authorization){
        log.info("Authorization = {}",Authorization);
        return ResponseEntity.ok().build();
    }

    @PutMapping("**")
    public ResponseEntity<?> verifyToken3(@RequestHeader("Authorization") String Authorization){
        log.info("Authorization = {}",Authorization);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("**")
    public ResponseEntity<?> verifyToken4(@RequestHeader("Authorization") String Authorization){
        log.info("Authorization = {}",Authorization);
        return ResponseEntity.ok().build();
    }
}
