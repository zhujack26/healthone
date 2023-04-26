package com.secui.healthone.domain.food.service;

import com.secui.healthone.domain.food.dto.CustomFoodResponseDto;
import com.secui.healthone.domain.food.entity.CustomFood;
import com.secui.healthone.domain.food.repository.CustomFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomFoodService {
    private final CustomFoodRepository customFoodRepository;

    public CustomFoodResponseDto getCustomFood(Integer no, Integer userNo) {
        Optional<CustomFood> result = customFoodRepository.findAllByNoAndUserNo(no, userNo);
        return result.map(CustomFoodResponseDto::new).orElse(null);
    }
}
