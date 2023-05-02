//package com.secui.healthone.food;
//
//import com.jayway.jsonpath.JsonPath;
//import com.secui.healthone.util.ApiTest;
//import io.restassured.response.ExtractableResponse;
//import io.restassured.response.Response;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpStatus;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@Slf4j
//public class FoodApiTest extends ApiTest {
//
//    @Test
//    @DisplayName("음식 데이터 단일 조회")
//    void getFoodData() {
//        Integer no = 1;
//        final ExtractableResponse<Response> response = FoodSteps.음식데이터_단일조회요청(no);
//        String responseBody = response.body().asString();
//        Integer resNo = JsonPath.read(responseBody, "$.data.no");
//        String resName = JsonPath.read(responseBody, "$.data.name");
//        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
//        assertThat(resNo).isEqualTo(1);
//        assertThat(resName).isEqualTo("가다랑어");
//    }
//
//    @Test
//    @DisplayName("음식 데이터 검색 조회")
//    void searchFoodData() {
//        String name = "김밥";
//        final ExtractableResponse<Response> response = FoodSteps.음식데이터_검색조회요청(name);
//        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
//        log.info("response : {}", response.asString());
//    }
//}
