package com.secui.healthone.global;

import com.secui.healthone.global.error.exception.RestApiException;
import com.secui.healthone.global.response.RestApiResponse;
import com.secui.healthone.global.error.errorcode.UserErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public RestApiResponse<String> testServer(){
        return new RestApiResponse<>("Meal Server is running", null);
    }

    @GetMapping("/error")
    public ResponseEntity<String> testError(){
        throw new RuntimeException("RUNTIME ERROR");
    }

    @GetMapping("/customerror")
    public RestApiException testCustomError(){
        throw new RestApiException(UserErrorCode.USER_401);
    }

}


