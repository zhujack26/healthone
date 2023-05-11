package com.secui.healthone.domain.healthInfo.service;

import com.secui.healthone.domain.healthInfo.dto.HealthInfoGetReqDto;
import com.secui.healthone.domain.healthInfo.dto.HealthInfoDto;

public interface HealthInfoService {
    HealthInfoDto getHealthInfo(HealthInfoGetReqDto getHealthInfoReqDto);

    HealthInfoDto addHealthInfo(HealthInfoDto healthInfoDto);

    HealthInfoDto updateHealthInfo(HealthInfoDto healthInfoDto);


    void deleteHealthInfo(String no);
}
