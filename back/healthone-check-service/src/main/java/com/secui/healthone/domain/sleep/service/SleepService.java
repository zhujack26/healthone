package com.secui.healthone.domain.sleep.service;

import com.secui.healthone.domain.sleep.dto.SleepInsertDto;
import com.secui.healthone.domain.sleep.dto.SleepResDto;
import com.secui.healthone.domain.sleep.dto.SleepUpdateDto;

import java.util.List;

public interface SleepService {

    List<SleepResDto> getSleepData(String date);

    void addSleepInfo(SleepInsertDto sleepInfoReqDto);

    void updateSleepInfo(SleepUpdateDto sleepInfoReqDto);

    void deleteSleepInfo(Integer no);

}
