package com.secui.healthone.domain.healthStat.service;

import com.secui.healthone.domain.healthStat.dto.HealthStatDeleteReqDto;
import com.secui.healthone.domain.healthStat.dto.HealthStatDto;

import java.util.List;

public interface HealthStatService {

    List<HealthStatDto> getHealthStat(String date);

    void addHealthStat(HealthStatDto healthStatDto);

    void updateHealthStat(HealthStatDto healthStatDto);

    void deleteHealthStat(HealthStatDeleteReqDto healthStatDeleteReqDto);

}
