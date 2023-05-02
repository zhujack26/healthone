//package com.secui.healthone.customfood;
//
//import com.jayway.jsonpath.JsonPath;
//import com.secui.healthone.util.ApiTest;
//import io.restassured.response.ExtractableResponse;
//import io.restassured.response.Response;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpStatus;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@Slf4j
//public class CustomFoodApiTest extends ApiTest {
//
//    @Test
//    @DisplayName("사용자 음식 데이터 단일 조회")
//    @Transactional
//    void getCustomFoodData() {
//        //given
//        CustomFoodSteps.사용자_음식데이터_등록요청(CustomFoodSteps.사용자_음식데이터_등록요청_생성1());
//        Integer no = 1;
//        //when
//        final ExtractableResponse<Response> response = CustomFoodSteps.사용자_음식데이터_단일조회요청(no);
//        //then
//        log.info("res :{}", response);
//        String resName = JsonPath.read(response.body().asString(), "$.data.name");
//        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
//        assertThat(resName).isEqualTo("돼지고기");
//    }
//
//    @Test
//    @DisplayName("사용자 음식 데이터 등록")
//    @Transactional
//    void insertCustomFoodData() {
//        //given
//        CustomFoodSteps.사용자_음식데이터_등록요청(CustomFoodSteps.사용자_음식데이터_등록요청_생성1());
//        CustomFoodSteps.사용자_음식데이터_등록요청(CustomFoodSteps.사용자_음식데이터_등록요청_생성2());
//        //when
//        final ExtractableResponse<Response> response1 = CustomFoodSteps.사용자_음식데이터_단일조회요청(1);
//        log.info(response1.body().asString());
//        String resName1 = JsonPath.read(response1.body().asString(), "$.data.name");
//        final ExtractableResponse<Response> response2 = CustomFoodSteps.사용자_음식데이터_단일조회요청(2);
//        String resName2 = JsonPath.read(response2.body().asString(), "$.data.name");
//        //then
//        assertThat(response1.statusCode()).isEqualTo(HttpStatus.OK.value());
//        assertThat(resName1).isEqualTo("돼지고기");
//        assertThat(response2.statusCode()).isEqualTo(HttpStatus.OK.value());
//        assertThat(resName2).isEqualTo("김치고기");
//    }
//
//    @Test
//    @DisplayName("사용자 음식 데이터 수정")
//    @Transactional
//    void modifyCustomFoodData() {
//        //given
//        CustomFoodSteps.사용자_음식데이터_등록요청(CustomFoodSteps.사용자_음식데이터_등록요청_생성1());
//        CustomFoodSteps.사용자_음식데이터_등록요청(CustomFoodSteps.사용자_음식데이터_등록요청_생성2());
//        //when
//        CustomFoodSteps.사용자_음식데이터_수정요청(CustomFoodSteps.사용자_음식데이터_수정요청_생성1());
//        CustomFoodSteps.사용자_음식데이터_수정요청(CustomFoodSteps.사용자_음식데이터_수정요청_생성2());
//        final ExtractableResponse<Response> response1 = CustomFoodSteps.사용자_음식데이터_단일조회요청(1);
//        String resName1 = JsonPath.read(response1.body().asString(), "$.data.name");
//        final ExtractableResponse<Response> response2 = CustomFoodSteps.사용자_음식데이터_단일조회요청(2);
//        String resName2 = JsonPath.read(response2.body().asString(), "$.data.name");
//        //then
//        assertThat(response1.statusCode()).isEqualTo(HttpStatus.OK.value());
//        assertThat(resName1).isEqualTo("만두라면");
//        assertThat(response2.statusCode()).isEqualTo(HttpStatus.OK.value());
//        assertThat(resName2).isEqualTo("참치라면");
//    }
//
//    @Test
//    @DisplayName("사용자 음식 데이터 삭제")
//    @Transactional
//    void deleteCustomFoodData() {
//        //given
//        CustomFoodSteps.사용자_음식데이터_등록요청(CustomFoodSteps.사용자_음식데이터_등록요청_생성1());
//        //when
//        final ExtractableResponse<Response> response1 = CustomFoodSteps.사용자_음식데이터_단일조회요청(1);
//        String resName1 = JsonPath.read(response1.body().asString(), "$.data.name");
//        final ExtractableResponse<Response> response2 = CustomFoodSteps.사용자_음식데이터_삭제요청(1);
//        String resMsg = JsonPath.read(response2.body().asString(), "$.message");
//        //then
//        assertThat(response1.statusCode()).isEqualTo(HttpStatus.OK.value());
//        assertThat(resName1).isEqualTo("돼지고기");
//
//        assertThat(response2.statusCode()).isEqualTo(HttpStatus.OK.value());
//        assertThat(resMsg).isEqualTo("사용자 음식 데이터 삭제 성공");
//    }
//}
