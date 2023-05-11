package com.secui.healthone.domain.healthInfo.api;

import com.secui.healthone.domain.healthInfo.dto.HealthInfoDto;
import com.secui.healthone.domain.healthInfo.dto.HealthInfoGetReqDto;
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
    public RestApiResponse<HealthInfoDto> getHealthInfo(@RequestBody HealthInfoGetReqDto getHealthInfoReqDto) {
        return new RestApiResponse<>("회원 헬스 데이터 조회 성공", healthInfoService.getHealthInfo(getHealthInfoReqDto));
    }

    @PostMapping
    public RestApiResponse<HealthInfoDto> addHealthInfo(@RequestBody HealthInfoDto healthInfoDto) {
        return new RestApiResponse<>("회원 헬스 데이터 등록 성공", healthInfoService.addHealthInfo(healthInfoDto));
    }

    @PatchMapping
    public RestApiResponse<HealthInfoDto> updateHealthInfo(@RequestBody HealthInfoDto healthInfoDto) {
        return new RestApiResponse<>("회원 헬스 데이터 수정 성공", healthInfoService.updateHealthInfo(healthInfoDto));
    }

    @DeleteMapping
    public RestApiResponse<?> deleteHealthInfo(@RequestParam String no) {
        healthInfoService.deleteHealthInfo(no);
        return new RestApiResponse<>("회원 헬스 데이터 삭제 성공", null);
    }

}
