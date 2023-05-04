package com.secui.healthone.domain.food.service;

import com.secui.healthone.domain.food.dto.FoodResponseDto;
import com.secui.healthone.domain.food.entity.Food;
import com.secui.healthone.domain.food.repository.FoodRepository;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodResponseDto getFood(Integer no) {
        Optional<Food> result = foodRepository.findById(no);
        return result.map(FoodResponseDto::new).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
    }

    public Slice<FoodResponseDto> searchFood(String name, Pageable pageable) {
        Slice<Food> result = foodRepository.findAllByNameContaining(name, pageable);
        return result.map(FoodResponseDto::new);
    }
}
