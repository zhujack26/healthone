package com.secui.healthone.domain.meal.api;

import com.secui.healthone.domain.meal.dto.MealRequestDto;
import com.secui.healthone.domain.meal.dto.MealResponseDto;
import com.secui.healthone.domain.meal.service.MealService;
import com.secui.healthone.global.response.RestApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/meal")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping
    public RestApiResponse<MealResponseDto> getMeal(@RequestParam("no") Integer no) {
        return new RestApiResponse<>("식사 정보 단일 조회 성공", mealService.getMeal(no));
    }

    @GetMapping("/list")
    public RestApiResponse<List<MealResponseDto>> getMealList(@RequestParam("date") String date) throws ParseException {
        return new RestApiResponse<>(date+ "날짜 식사 리스트 조회 성공", mealService.getMealList(date));
    }
    
    @PostMapping
    public RestApiResponse<MealResponseDto> insertMeal(@RequestBody MealRequestDto requestDto) {
        return new RestApiResponse<>("식사 등록 성공", mealService.insertMeal(requestDto));
    }

    @PatchMapping
    public RestApiResponse<MealResponseDto> updateMeal(@RequestBody MealRequestDto requestDto) {
        return new RestApiResponse<>("식사 수정 성공", mealService.updateMeal(requestDto));
    }

    @DeleteMapping
    public RestApiResponse<Void> updateMeal(@RequestParam("no") Integer no) {
        mealService.deleteMeal(no);
        return new RestApiResponse<>("식사 삭제 성공", null);
    }
}
