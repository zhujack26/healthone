package com.secui.healthone.domain.food.service;

import com.secui.healthone.domain.food.dto.CustomFoodReqDto;
import com.secui.healthone.domain.food.dto.CustomFoodResDto;
import com.secui.healthone.domain.food.entity.CustomFood;
import com.secui.healthone.domain.food.repository.CustomFoodRepository;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomFoodService {
    private final CustomFoodRepository customFoodRepository;

    public CustomFoodResDto getCustomFood(Integer no, Integer userNo) {
        Optional<CustomFood> result = customFoodRepository.findAllByNoAndUserNo(no, userNo);
        return result.map(CustomFoodResDto::new).orElse(null);
    }

    public List<CustomFoodResDto> searchCustomFood(String name, Integer userNo) {
        List<CustomFood> result = customFoodRepository.findAllByUserNoAndNameContaining(userNo, name);
        return result.stream()
                .map(CustomFoodResDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public CustomFoodResDto saveCustomFood(CustomFoodReqDto requestDto) {
        CustomFood result = customFoodRepository.save(requestDto.toEntity());
        return new CustomFoodResDto(result);
    }

    @Transactional
    public CustomFoodResDto updateCustomFood(CustomFoodReqDto requestDto) {
        CustomFood result = customFoodRepository.findAllByNoAndUserNo(requestDto.getNo(), requestDto.getUserNo())
                .orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        result.update(requestDto.getName(), requestDto.getKcal(),  requestDto.getGram());
        return new CustomFoodResDto(result);
    }

    @Transactional
    public void deleteCustomFood(Integer no, Integer userNo) {
        customFoodRepository.deleteAllByNoAndUserNo(no, userNo);
    }
}
