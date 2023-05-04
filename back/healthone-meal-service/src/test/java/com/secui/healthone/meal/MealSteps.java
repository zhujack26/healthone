//package com.secui.healthone.meal;
//
//import com.secui.healthone.domain.meal.dto.MealReqDto;
//import com.secui.healthone.domain.meal.entity.MealType;
//import io.restassured.RestAssured;
//import io.restassured.response.ExtractableResponse;
//import io.restassured.response.Response;
//import org.springframework.http.MediaType;
//
//import java.time.LocalDateTime;
//
//public class MealSteps {
//
//    public static MealReqDto 일반_식단_등록요청_생성1() {
//        Integer no = null;
//        Integer userNo = 1;
//        Integer foodNo = 1;
//        Integer customfoodNo = null;
//        LocalDateTime createTime = LocalDateTime.now();
//        MealType mealType = MealType.BREAKFAST;
//        Float portion = 2.0f;
//        Float gram =  100.0f;
//        Integer kcal = 143;
//        return new MealReqDto(no, userNo, foodNo, customfoodNo, createTime, mealType, portion, gram, kcal);
//    }
//
//    public static MealReqDto 일반_식단_등록요청_생성2() {
//        Integer no = null;
//        Integer userNo = 1;
//        Integer foodNo = 3;
//        Integer customfoodNo = null;
//        LocalDateTime createTime = LocalDateTime.now();
//        MealType mealType = MealType.BREAKFAST;
//        Float portion = 4.0f;
//        Float gram =  400.0f;
//        Integer kcal = 343;
//        return new MealReqDto(no, userNo, foodNo, customfoodNo, createTime, mealType, portion, gram, kcal);
//    }
//
//    public static MealReqDto 사용자_식단_등록요청_생성1() {
//        Integer no = null;
//        Integer userNo = 1;
//        Integer foodNo = null;
//        Integer customfoodNo = 1;
//        LocalDateTime createTime = LocalDateTime.now();
//        MealType mealType = MealType.BREAKFAST;
//        Float portion = 2.0f;
//        Float gram =  100.0f;
//        Integer kcal = 145;
//        return new MealReqDto(no, userNo, foodNo, customfoodNo, createTime, mealType, portion, gram, kcal);
//    }
//
//    public static MealReqDto 사용자_식단_등록요청_생성2() {
//        Integer no = null;
//        Integer userNo = 1;
//        Integer foodNo = null;
//        Integer customfoodNo = 2;
//        LocalDateTime createTime = LocalDateTime.now();
//        MealType mealType = MealType.LUNCH;
//        Float portion = 50.0f;
//        Float gram =  150.0f;
//        Integer kcal = 555;
//        return new MealReqDto(no, userNo, foodNo, customfoodNo, createTime, mealType, portion, gram, kcal);
//    }
//
//    public static ExtractableResponse<Response> 식단_단일조회요청(Integer no) {
//        return RestAssured.given().log().all()
//                .when()
//                .get("/api/meal?no={no}", no)
//                .then()
//                .log().all()
//                .extract();
//    }
//
//    public static ExtractableResponse<Response> 식단_리스트조회요청(String date, Integer userNo) {
//        return RestAssured.given().log().all()
//                .when()
//                .get("/api/meal/list?date={date}&userno={userNo}", date, userNo)
//                .then()
//                .log().all()
//                .extract();
//    }
//
//    public static ExtractableResponse<Response> 식단_등록요청(MealReqDto request) {
//        return RestAssured.given().log().all()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .body(request)
//                .when()
//                .post("/api/meal")
//                .then()
//                .log().all()
//                .extract();
//    }
//
//    public static ExtractableResponse<Response> 식단_수정요청(MealReqDto request) {
//        return RestAssured.given().log().all()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .body(request)
//                .when()
//                .patch("/api/meal")
//                .then()
//                .log().all()
//                .extract();
//    }
//
//    public static MealReqDto 일반_식단_수정요청_생성1() {
//        Integer no = 1;
//        Integer userNo = 1;
//        Integer foodNo = 6;
//        Integer customfoodNo = null;
//        LocalDateTime createTime = LocalDateTime.now();
//        MealType mealType = MealType.BREAKFAST;
//        Float portion = 4.0f;
//        Float gram =  555555.0f;
//        Integer kcal = 544050;
//        return new MealReqDto(no, userNo, foodNo, customfoodNo, createTime, mealType, portion, gram, kcal);
//    }
//
//    public static ExtractableResponse<Response> 식단_삭제요청(Integer no) {
//        return RestAssured.given().log().all()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .when()
//                .delete("/api/meal?no={no}", no)
//                .then()
//                .log().all()
//                .extract();
//    }
//
//}
