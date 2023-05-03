package com.secui.healthone.domain.heartRate.service;

import com.secui.healthone.domain.heartRate.dto.AddHeartRateInfoReqDto;
import com.secui.healthone.domain.heartRate.dto.HeartRateResDto;

import java.util.List;

public interface HeartRateService {
    HeartRateResDto addHeartRateInfo(AddHeartRateInfoReqDto addHeartRateInfoReqDto);

    void deleteHeartRateInfo(Integer no);

    List<HeartRateResDto> getWeeklyHeartRate(String dateTime);
}
