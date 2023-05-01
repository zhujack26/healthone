package com.secui.healthone.domain.food.service;

import com.secui.healthone.domain.food.dto.FoodResponseDto;
import com.secui.healthone.domain.food.entity.Food;
import com.secui.healthone.domain.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodResponseDto getFood(Integer no) {
        Optional<Food> result = foodRepository.findById(no);
        return result.map(FoodResponseDto::new).orElse(null);
    }

    public List<FoodResponseDto> searchFood(String name) {
        List<Food> result = foodRepository.findAllByNameContaining(name);
        return result.stream()
                .map(FoodResponseDto::new)
                .collect(Collectors.toList());
    }
}
