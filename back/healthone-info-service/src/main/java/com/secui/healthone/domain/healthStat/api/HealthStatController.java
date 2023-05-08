package com.secui.healthone.domain.healthStat.api;

import com.secui.healthone.domain.healthStat.dto.HealthStatDeleteReqDto;
import com.secui.healthone.domain.healthStat.dto.HealthStatDto;
import com.secui.healthone.domain.healthStat.service.HealthStatService;
import com.secui.healthone.global.response.RestApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/health-stat")
public class HealthStatController {

    private final HealthStatService healthStatService;

    @GetMapping
    public RestApiResponse<?> getHealthStat(@RequestParam String date) {
        List<HealthStatDto> healthStatDtoList = healthStatService.getHealthStat(date);
        return new RestApiResponse<>("건강 기록 조회 성공", healthStatDtoList);
    }

    @PostMapping
    public RestApiResponse<?> addHealthStat(@RequestBody HealthStatDto healthStatDto) {
        healthStatService.addHealthStat(healthStatDto);
        return new RestApiResponse<>("건강 기록 등록 성공", null);
    }

    @PatchMapping
    public RestApiResponse<?> updateHealthStat(@RequestBody HealthStatDto healthStatDto) {
        healthStatService.updateHealthStat(healthStatDto);
        return new RestApiResponse<>("건강 기록 수정 성공", null);
    }

    @DeleteMapping
    public RestApiResponse<?> deleteHealthStat(@RequestBody HealthStatDeleteReqDto healthStatDeleteReqDto) {
        healthStatService.deleteHealthStat(healthStatDeleteReqDto);
        return new RestApiResponse<>("건강 기록 삭제 성공", null);
    }

}
