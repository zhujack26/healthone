package com.secui.healthone.domain.sleep.service;

import com.secui.healthone.domain.sleep.dto.SleepInsertDto;
import com.secui.healthone.domain.sleep.dto.SleepUpdateDto;
import com.secui.healthone.domain.sleep.entity.Sleep;

import java.util.List;

public interface SleepService {

    List<Sleep> getSleepData(String date);

    void addSleepInfo(SleepInsertDto sleepInfoReqDto);

    void updateSleepInfo(SleepUpdateDto sleepInfoReqDto);

    void deleteSleepInfo(String no);

}
