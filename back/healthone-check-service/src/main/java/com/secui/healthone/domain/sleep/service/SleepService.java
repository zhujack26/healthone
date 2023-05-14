package com.secui.healthone.domain.sleep.service;

import com.secui.healthone.domain.sleep.dto.SleepInsertDto;
import com.secui.healthone.domain.sleep.dto.SleepResDto;
import com.secui.healthone.domain.sleep.dto.SleepUpdateDto;

import java.util.List;

public interface SleepService {
    List<SleepResDto> getSleepData(String date, Integer userN);
    SleepResDto addSleepInfo(SleepInsertDto sleepInfoReqDto);
    SleepResDto updateSleepInfo(SleepUpdateDto sleepInfoReqDto);
    void deleteSleepInfo(Integer no, Integer userNo);
    List<SleepResDto> getSleepDataList(Integer userNo, String date);
}
