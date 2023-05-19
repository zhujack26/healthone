package com.secui.healthone.domain.heartRate.service;

import com.secui.healthone.domain.heartRate.dto.HeartRateInsertDto;
import com.secui.healthone.domain.heartRate.dto.HeartRateResDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface HeartRateService {
    HeartRateResDto addHeartRateInfo(HeartRateInsertDto heartRateInsertDto);

    void deleteHeartRateInfo(Integer no, Integer userNo);

    Slice<HeartRateResDto> getHeartRateList(Integer userNo, Pageable pageable);

//    List<HeartRateResDto> getWeeklyHeartRate(String dateTime);
}
