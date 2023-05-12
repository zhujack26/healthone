package com.secui.healthone.domain.healthInfo.service;

import com.secui.healthone.domain.healthInfo.dto.HealthInfoDto;

public interface HealthInfoService {
    HealthInfoDto getHealthInfo(Integer no, Integer userNo);
    HealthInfoDto addHealthInfo(HealthInfoDto healthInfoDto);
    HealthInfoDto updateHealthInfo(HealthInfoDto healthInfoDto);
    void deleteHealthInfo(Integer no);
}
