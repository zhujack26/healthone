package com.secui.healthone.domain.healthAdvice.api;

import com.secui.healthone.domain.healthAdvice.dto.GetHealthAdviceReqDto;
import com.secui.healthone.domain.healthAdvice.dto.HealthAdviceDeleteReqDto;
import com.secui.healthone.domain.healthAdvice.dto.HealthAdviceDto;
import com.secui.healthone.domain.healthAdvice.service.HealthAdviceService;
import com.secui.healthone.global.response.RestApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/health-adivce")
public class HealthAdviceController {

    private final HealthAdviceService healthAdviceService;

    @GetMapping
    public RestApiResponse<List<HealthAdviceDto>> getHealthAdvice(@RequestBody GetHealthAdviceReqDto getHealthAdviceReqDto) {
        List<HealthAdviceDto> healthAdviceResDtoList = healthAdviceService.getHealthAdvice(getHealthAdviceReqDto);
        return new RestApiResponse<>("회원 건강 조언 조회 성공", healthAdviceResDtoList);
    }

    @DeleteMapping
    public RestApiResponse<?> deleteHealthAdvice(@RequestBody HealthAdviceDeleteReqDto healthAdviceDeleteReqDto) {
        healthAdviceService.deleteHealthAdvice(healthAdviceDeleteReqDto);
        return new RestApiResponse<>("회원 건강 조언 삭제 성공", null);
    }
}
