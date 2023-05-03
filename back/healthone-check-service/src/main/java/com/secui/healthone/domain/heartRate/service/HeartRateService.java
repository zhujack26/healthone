package com.secui.healthone.domain.heartRate.service;

import com.secui.healthone.domain.heartRate.dto.AddHeartRateInfoReqDto;
import com.secui.healthone.domain.heartRate.dto.HeartRateResDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface HeartRateService {
    HeartRateResDto addHeartRateInfo(AddHeartRateInfoReqDto addHeartRateInfoReqDto);

    void deleteHeartRateInfo(Integer no);

    Slice<HeartRateResDto> getHeartRateList(Integer userNo, Pageable pageable);

//    List<HeartRateResDto> getWeeklyHeartRate(String dateTime);
}
