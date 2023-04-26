package com.secui.healthone.domain.food.api;

import com.secui.healthone.domain.food.dto.FoodResponseDto;
import com.secui.healthone.domain.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping
    public ResponseEntity<FoodResponseDto> getFood(@RequestParam("no") Integer no) {
        return ResponseEntity.ok(foodService.getFood(no));
    }

    @GetMapping("/search")
    public ResponseEntity<List<FoodResponseDto>> searchFood(@RequestParam("name") String name) {
        return ResponseEntity.ok(foodService.searchFood(name));
    }


}
