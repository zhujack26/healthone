package com.secui.healthone.domain.calorie.service;

import com.secui.healthone.domain.calorie.dto.CalorieResponseDto;
import com.secui.healthone.domain.calorie.repository.CalorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalorieService {
    private CalorieRepository calorieRepository;

    // TODO: 소모 칼로리, 섭취 칼로리 로직 추가 필요
    public CalorieResponseDto getCalorie(String date) {
        return null;
    }
}
