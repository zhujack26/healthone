package com.secui.healthone.domain.food.service;

import com.secui.healthone.domain.food.dto.CustomFoodResponseDto;
import com.secui.healthone.domain.food.dto.FoodResponseDto;
import com.secui.healthone.domain.food.entity.CustomFood;
import com.secui.healthone.domain.food.entity.Food;
import com.secui.healthone.domain.food.repository.CustomFoodRepository;
import com.secui.healthone.domain.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final CustomFoodRepository customFoodRepository;

    public FoodResponseDto getFood(Integer no) {
        Optional<Food> result = foodRepository.findById(no);
        return result.map(FoodResponseDto::new).orElse(null);
    }

    public CustomFoodResponseDto getCustomFood(Integer no, Integer userNo) {
        Optional<CustomFood> result = customFoodRepository.findAllByNoAndUserNo(no, userNo);
        return result.map(CustomFoodResponseDto::new).orElse(null);
    }

}
