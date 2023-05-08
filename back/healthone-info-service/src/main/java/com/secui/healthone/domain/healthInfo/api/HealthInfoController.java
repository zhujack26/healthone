package com.secui.healthone.domain.healthInfo.api;

import com.secui.healthone.domain.healthInfo.dto.GetHealthInfoReqDto;
import com.secui.healthone.domain.healthInfo.dto.HealthInfoDeleteReqDto;
import com.secui.healthone.domain.healthInfo.dto.HealthInfoDto;
import com.secui.healthone.domain.healthInfo.service.HealthInfoService;
import com.secui.healthone.global.response.RestApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/health-info")
public class HealthInfoController {

    private final HealthInfoService healthInfoService;

    @GetMapping
    public RestApiResponse<HealthInfoDto> getHealthInfo(@RequestBody GetHealthInfoReqDto getHealthInfoReqDto) {
        HealthInfoDto healthInfoDto = healthInfoService.getHealthInfo(getHealthInfoReqDto);
        return new RestApiResponse<>("", healthInfoDto);
    }
    @PostMapping
    public RestApiResponse<?> addHealthInfo(@RequestBody HealthInfoDto healthInfoDto) {
        healthInfoService.addHealthInfo(healthInfoDto);
        return new RestApiResponse<>("회원 헬스 데이터 등록 성공", null);
    }

    @PatchMapping
    public RestApiResponse<?> updateHealthInfo(@RequestBody HealthInfoDto healthInfoDto) {
        healthInfoService.updateHealthInfo(healthInfoDto);
        return new RestApiResponse<>("회원 헬스 데이터 수정 성공", null);
    }

    @DeleteMapping
    public RestApiResponse<?> deleteHealthInfo(@RequestParam String no) {
        healthInfoService.deleteHealthInfo(no);
        return new RestApiResponse<>("회원 헬스 데이터 삭제 성공", null);
    }

}
