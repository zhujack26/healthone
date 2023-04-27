package com.secui.healthone.customfood;

import com.secui.healthone.ApiResetTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
public class CustomFoodApiTest extends ApiResetTest {

    @Test
    @DisplayName("사용자 음식 데이터 단일 조회")
    void getCustomFoodData() {
        final ExtractableResponse<Response> response
                = CustomFoodSteps.사용자_음식데이터_등록요청(CustomFoodSteps.사용자_음식데이터_등록요청_생성());
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("name")).isEqualTo("돼지고기");
    }

    @Test
    @DisplayName("사용자 음식 데이터 등록")
    void insertCustomFoodData() {
        Integer no = 1;
        final ExtractableResponse<Response> response = CustomFoodSteps.사용자_음식데이터_단일조회요청(no);
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        log.info(response.asString());
    }
}
