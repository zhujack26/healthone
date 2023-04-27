package com.secui.healthone.domain.calorie.service;

import com.secui.healthone.domain.calorie.dto.CalorieResponseDto;
import com.secui.healthone.domain.calorie.repository.CalorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalorieService {
    private CalorieRepository calorieRepository;

    public CalorieResponseDto getCalorie(String date) {
        return null;
    }
}
