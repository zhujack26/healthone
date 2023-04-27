package com.secui.healthone.food;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class FoodSteps {

    public static ExtractableResponse<Response> 음식데이터_단일조회요청(Integer no) {
        return RestAssured.given().log().all()
                .when()
                .get("/api/food?no={no}", no)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 음식데이터_검색조회요청(String name) {
        return RestAssured.given().log().all()
                .when()
                .get("/api/food/search?name={name}", name)
                .then()
                .log().all()
                .extract();
    }
}
