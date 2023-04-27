package com.secui.healthone.domain.food.api;

import com.secui.healthone.domain.food.dto.CustomFoodResponseDto;
import com.secui.healthone.domain.food.dto.FoodResponseDto;
import com.secui.healthone.domain.food.service.CustomFoodService;
import com.secui.healthone.global.response.RestApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customfood")
@RequiredArgsConstructor
public class CustomFoodController {

    private final CustomFoodService customFoodService;
    
    // 사용자 음식 데이터 단건 조회
    @GetMapping
    public RestApiResponse<CustomFoodResponseDto> getCustomFood(@RequestParam("no") Integer no, @RequestParam("userNo") Integer userNo) {
        return new RestApiResponse<>("사용자 음식 데이터 단건 조회 성공", customFoodService.getCustomFood(no, userNo));
    }

    // 사용자 음식 데이터 검색
    @GetMapping("/serarch")
    public RestApiResponse<List<CustomFoodResponseDto>> getCustomFood(@RequestParam("name") String name, @RequestParam("userNo") Integer userNo) {
        return new RestApiResponse<>("사용자 음식 데이터 검색 성공", customFoodService.searchCustomFood(name, userNo));
    }

    // 사용자 음식 등록
//    @PostMapping
//    public RestApiResponse<CustomFoodResponseDto> getCustomFood(@RequestParam("no") Integer no, @RequestParam("userNo") Integer userNo) {
//        return new RestApiResponse<>("사용자 음식 데이터 검색 성공", );
//    }

}
