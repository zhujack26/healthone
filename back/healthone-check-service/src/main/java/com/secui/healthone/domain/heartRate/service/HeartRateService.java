package com.secui.healthone.domain.heartRate.service;

import com.secui.healthone.domain.heartRate.dto.AddHeartRateInfoReqDto;
import com.secui.healthone.domain.heartRate.entity.HeartRate;

import java.util.List;

public interface HeartRateService {
    void addHeartRateInfo(AddHeartRateInfoReqDto addHeartRateInfoReqDto);

    void deleteHeartRateInfo(String no);

    List<HeartRate> getWeeklyHeartRate(String dateTime);
}
