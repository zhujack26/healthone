package com.secui.healthone.domain.calorie.api;

import com.secui.healthone.domain.calorie.dto.CalorieResponseDto;
import com.secui.healthone.domain.calorie.service.CalorieService;
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

    private final CalorieService calorieService;

    // 소모 칼로리 정보 출력
    @GetMapping
    public RestApiResponse<CalorieResponseDto> getMeal(@RequestParam("date") String date) {
        Integer userNo = 1;
        return new RestApiResponse<>(date + "날짜 섭취, 소모 칼로리 조회 성공", calorieService.getCalorie(date, userNo));
    }
}
