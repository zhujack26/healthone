package com.secui.healthone.domain.food.service;

import com.secui.healthone.domain.food.dto.CustomFoodResponseDto;
import com.secui.healthone.domain.food.dto.FoodResponseDto;
import com.secui.healthone.domain.food.entity.CustomFood;
import com.secui.healthone.domain.food.entity.Food;
import com.secui.healthone.domain.food.repository.CustomFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomFoodService {
    private final CustomFoodRepository customFoodRepository;

    public CustomFoodResponseDto getCustomFood(Integer no, Integer userNo) {
        Optional<CustomFood> result = customFoodRepository.findAllByNoAndUserNo(no, userNo);
        return result.map(CustomFoodResponseDto::new).orElse(null);
    }

    public List<CustomFoodResponseDto> searchCustomFood(String name, Integer userNo) {
        List<CustomFood> result = customFoodRepository.findAllByUserNoAndNameContaining(userNo, name);
        return result.stream()
                .map(CustomFoodResponseDto::new)
                .collect(Collectors.toList());
    }
}
