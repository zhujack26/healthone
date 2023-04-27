package com.secui.healthone.domain.calorie.api;

import com.secui.healthone.domain.calorie.service.CalorieService;
import com.secui.healthone.domain.meal.dto.MealResponseDto;
import com.secui.healthone.global.response.RestApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calorie")
@RequiredArgsConstructor
public class CalorieController {

    private CalorieService calorieService;

    // 소모 칼로리 정보 출력
    @GetMapping
    public RestApiResponse<MealResponseDto> getMeal(@RequestParam("date") String date) {
        return new RestApiResponse<>(date + "날짜 소모 칼로리 조회 성공", calorieService.getCalorie(date));
    }
}
