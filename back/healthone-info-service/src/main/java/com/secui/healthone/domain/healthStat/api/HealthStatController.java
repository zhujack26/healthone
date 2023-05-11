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
    public RestApiResponse<List<HealthStatDto>> getHealthStat(@RequestParam String date) {
        return new RestApiResponse<>("건강 기록 조회 성공", healthStatService.getHealthStat(date));
    }

    @PostMapping
    public RestApiResponse<HealthStatDto> addHealthStat(@RequestBody HealthStatDto healthStatDto) {
        return new RestApiResponse<>("건강 기록 등록 성공", healthStatService.addHealthStat(healthStatDto));
    }

    @PatchMapping
    public RestApiResponse<HealthStatDto> updateHealthStat(@RequestBody HealthStatDto healthStatDto) {
        return new RestApiResponse<>("건강 기록 수정 성공", healthStatService.updateHealthStat(healthStatDto));
    }

    @DeleteMapping
    public RestApiResponse<?> deleteHealthStat(@RequestBody HealthStatDeleteReqDto healthStatDeleteReqDto) {
        healthStatService.deleteHealthStat(healthStatDeleteReqDto);
        return new RestApiResponse<>("건강 기록 삭제 성공", null);
    }

}
