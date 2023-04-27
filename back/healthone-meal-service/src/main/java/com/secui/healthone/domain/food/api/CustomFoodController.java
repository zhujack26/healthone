package com.secui.healthone.domain.food.api;

import com.secui.healthone.domain.food.dto.CustomFoodRequestDto;
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
    

    @GetMapping
    public RestApiResponse<CustomFoodResponseDto> getCustomFood(@RequestParam("no") Integer no) {
        Integer userNo = 1;
        return new RestApiResponse<>("사용자 음식 데이터 단건 조회 성공", customFoodService.getCustomFood(no, userNo));
    }


    @GetMapping("/serarch")
    public RestApiResponse<List<CustomFoodResponseDto>> getCustomFood(@RequestParam("name") String name) {
        Integer userNo = 1;
        return new RestApiResponse<>("사용자 음식 데이터 검색 성공", customFoodService.searchCustomFood(name, userNo));
    }


    @PostMapping
    public RestApiResponse<CustomFoodResponseDto> getCustomFood(@RequestBody CustomFoodRequestDto requestDto) {
        return new RestApiResponse<>("사용자 음식 데이터 등록 성공", customFoodService.saveCustomFood(requestDto));
    }

    @PatchMapping
    public RestApiResponse<CustomFoodResponseDto> modifyCustomFood(@RequestBody CustomFoodRequestDto requestDto) {
        return new RestApiResponse<>("사용자 음식 데이터 수정 성공", customFoodService.updateCustomFood(requestDto));
    }

    @DeleteMapping
    public RestApiResponse<CustomFoodResponseDto> deleteCustomFood(@RequestParam Integer no) {
        customFoodService.deleteCustomFood(no);
        return new RestApiResponse<>("사용자 음식 데이터 삭제 성공", null);
    }
}
