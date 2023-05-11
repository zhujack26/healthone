package com.secui.healthone.domain.healthInfo.service;

import com.secui.healthone.domain.healthInfo.dto.HealthInfoDto;

public interface HealthInfoService {
    HealthInfoDto getHealthInfo(Integer no, Integer userNo);

    void addHealthInfo(HealthInfoDto healthInfoDto);

    void updateHealthInfo(HealthInfoDto healthInfoDto);


    void deleteHealthInfo(String no);
}
