package com.secui.healthone.global.error.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    USER_400(HttpStatus.OK, "로그인 실패 : ID, PW 확인"),
    USER_401(HttpStatus.OK, "DB 회원 아이디 중복됨"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}