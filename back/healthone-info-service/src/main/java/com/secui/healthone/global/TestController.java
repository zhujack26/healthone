package com.secui.healthone.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public ResponseEntity<String> testServer(){
        return ResponseEntity.status(HttpStatus.OK).body("HealthInfo service SERVER ON");
    }

    @GetMapping("/error")
    public ResponseEntity<String> testError(){
        throw new RuntimeException();
    }
}

