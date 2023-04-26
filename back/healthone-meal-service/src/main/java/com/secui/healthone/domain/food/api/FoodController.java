package com.secui.healthone.domain.food.api;

import com.secui.healthone.domain.food.dto.CustomFoodResponseDto;
import com.secui.healthone.domain.food.dto.FoodResponseDto;
import com.secui.healthone.domain.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping("/food")
    public ResponseEntity<FoodResponseDto> getFood(@RequestParam("no") Integer no) {
        return ResponseEntity.ok(foodService.getFood(no));
    }

    @GetMapping("/customfood")
    public ResponseEntity<CustomFoodResponseDto> getCustomFood(@RequestParam("no") Integer no, @RequestParam("userNo") Integer userNo) {
        return ResponseEntity.ok(foodService.getCustomFood(no, userNo));
    }

}
