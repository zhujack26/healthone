package com.secui.healthone.domain.food.api;

import com.secui.healthone.domain.food.dto.CustomFoodResponseDto;
import com.secui.healthone.domain.food.service.CustomFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customfood")
@RequiredArgsConstructor
public class CustomFoodController {

    private final CustomFoodService customFoodService;
    @GetMapping
    public ResponseEntity<CustomFoodResponseDto> getCustomFood(@RequestParam("no") Integer no, @RequestParam("userNo") Integer userNo) {
        return ResponseEntity.ok(customFoodService.getCustomFood(no, userNo));
    }
}
