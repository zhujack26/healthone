package com.secui.healthone.domain.healthStat.service;

import com.secui.healthone.domain.healthStat.dto.HealthStatDto;

import java.util.List;

public interface HealthStatService {

    List<HealthStatDto> getHealthStat(String date);

    HealthStatDto addHealthStat(HealthStatDto healthStatDto);

    HealthStatDto updateHealthStat(HealthStatDto healthStatDto);

    void deleteHealthStat(Integer no, Integer userNo);

}
