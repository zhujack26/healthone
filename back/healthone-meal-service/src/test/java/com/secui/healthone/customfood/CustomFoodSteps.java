package com.secui.healthone.customfood;

import com.secui.healthone.domain.food.dto.CustomFoodRequestDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class CustomFoodSteps {

    public static ExtractableResponse<Response> 사용자_음식데이터_단일조회요청(Integer no) {
        return RestAssured.given().log().all()
                .when()
                .get("/api/customfood?no={no}", no)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 사용자_음식데이터_등록요청(CustomFoodRequestDto request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/api/customfood")
                .then()
                .log().all()
                .extract();
    }

    public static CustomFoodRequestDto 사용자_음식데이터_등록요청_생성() {
        final Integer userNo = 1;
        final String name = "돼지고기";
        final int kcal = 100;
        final float gram = 150;
        return new CustomFoodRequestDto(userNo, name, kcal, gram);
    }

    public static CustomFoodRequestDto 사용자_음식데이터_등록요청_생성2() {
        final Integer userNo = 1;
        final String name = "김치고기";
        final int kcal = 200;
        final float gram = 350;
        return new CustomFoodRequestDto(userNo, name, kcal, gram);
    }
}
