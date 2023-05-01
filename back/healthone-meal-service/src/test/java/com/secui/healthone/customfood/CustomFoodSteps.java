package com.secui.healthone.customfood;

import com.secui.healthone.domain.food.dto.CustomFoodReqDto;
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

    public static ExtractableResponse<Response> 사용자_음식데이터_등록요청(CustomFoodReqDto request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/api/customfood")
                .then()
                .log().all()
                .extract();
    }

    public static CustomFoodReqDto 사용자_음식데이터_등록요청_생성1() {
        final Integer userNo = 1;
        final String name = "돼지고기";
        final int kcal = 100;
        final float gram = 150;
        return CustomFoodReqDto.builder().userNo(userNo).name(name).kcal(kcal).gram(gram).build();
    }

    public static CustomFoodReqDto 사용자_음식데이터_등록요청_생성2() {
        final Integer userNo = 1;
        final String name = "김치고기";
        final int kcal = 200;
        final float gram = 350;
        return CustomFoodReqDto.builder().userNo(userNo).name(name).kcal(kcal).gram(gram).build();
    }

    public static CustomFoodReqDto 사용자_음식데이터_수정요청_생성1() {
        final Integer no = 1;
        final Integer userNo = 1;
        final String name = "만두라면";
        final int kcal = 500;
        final float gram = 650;
        return CustomFoodReqDto.builder().no(no).userNo(userNo).name(name).kcal(kcal).gram(gram).build();
    }

    public static CustomFoodReqDto 사용자_음식데이터_수정요청_생성2() {
        final Integer no = 2;
        final Integer userNo = 1;
        final String name = "참치라면";
        final int kcal = 700;
        final float gram = 450;
        return CustomFoodReqDto.builder().no(no).userNo(userNo).name(name).kcal(kcal).gram(gram).build();
    }

    public static ExtractableResponse<Response> 사용자_음식데이터_수정요청(CustomFoodReqDto request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .patch("/api/customfood")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 사용자_음식데이터_삭제요청(Integer no) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/api/customfood?no={no}", no)
                .then()
                .log().all()
                .extract();
    }
}
