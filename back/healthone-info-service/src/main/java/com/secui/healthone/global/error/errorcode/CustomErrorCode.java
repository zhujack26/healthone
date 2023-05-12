package com.secui.healthone.global.error.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode implements ErrorCode {
    DB_100(HttpStatus.OK, "DB에 해당 데이터를 찾을 수 없음"),
    STAT_400(HttpStatus.OK, "중복된 날짜로 건강기록을 기록할 수 없음"),
    FOOD_400(HttpStatus.OK, "로그인 실패 : ID, PW 확인"),
    USER_401(HttpStatus.OK, "DB 회원 아이디 중복됨"),
    USER_402(HttpStatus.OK, "DB에 중복된 정보가 있음")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}