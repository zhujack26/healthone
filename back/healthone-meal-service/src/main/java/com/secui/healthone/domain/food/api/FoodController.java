package com.secui.healthone.domain.food.api;

import com.secui.healthone.domain.food.dto.FoodResponseDto;
import com.secui.healthone.domain.food.service.FoodService;
import com.secui.healthone.global.response.RestApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping
    public RestApiResponse<FoodResponseDto> getFood(@RequestParam("no") Integer no) {
        return new RestApiResponse<>("음식 데이터 단일 조회 성공", foodService.getFood(no));
    }

    @GetMapping("/search")
    public RestApiResponse<List<FoodResponseDto>> searchFood(@RequestParam("name") String name) {
        return new RestApiResponse<>("음식 검색 조회 성공", foodService.searchFood(name));
    }

}
