package com.secui.healthone.domain.healthInfo.service;

import com.secui.healthone.domain.healthInfo.dto.GetHealthInfoReqDto;
import com.secui.healthone.domain.healthInfo.dto.HealthInfoDeleteReqDto;
import com.secui.healthone.domain.healthInfo.dto.HealthInfoDto;

public interface HealthInfoService {
    HealthInfoDto getHealthInfo(GetHealthInfoReqDto getHealthInfoReqDto);

    void addHealthInfo(HealthInfoDto healthInfoDto);

    void updateHealthInfo(HealthInfoDto healthInfoDto);


    void deleteHealthInfo(String no);
}
