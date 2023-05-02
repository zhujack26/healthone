package com.secui.healthone.domain.sleep.service;

import com.secui.healthone.domain.sleep.dto.AddSleepInfoReqDto;
import com.secui.healthone.domain.sleep.dto.UpdateSleepInfoReqDto;
import com.secui.healthone.domain.sleep.entity.Sleep;

import java.util.List;

public interface SleepService {

    List<Sleep> getSleepData(String date);

    void addSleepInfo(AddSleepInfoReqDto sleepInfoReqDto);

    void updateSleepInfo(UpdateSleepInfoReqDto sleepInfoReqDto);

    void deleteSleepInfo(String no);

}
