package com.secui.healthone.domain.food.api;

import com.secui.healthone.domain.food.dto.CustomFoodResponseDto;
import com.secui.healthone.domain.food.service.CustomFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customfood")
@RequiredArgsConstructor
public class CustomFoodController {

    private final CustomFoodService customFoodService;
    @GetMapping
    public ResponseEntity<CustomFoodResponseDto> getCustomFood(@RequestParam("no") Integer no, @RequestParam("userNo") Integer userNo) {
        return ResponseEntity.ok(customFoodService.getCustomFood(no, userNo));
    }

    @GetMapping("/serarch")
    public ResponseEntity<List<CustomFoodResponseDto>> getCustomFood(@RequestParam("name") String name, @RequestParam("userNo") Integer userNo) {
        return ResponseEntity.ok(customFoodService.searchCustomFood(name, userNo));
    }


}
