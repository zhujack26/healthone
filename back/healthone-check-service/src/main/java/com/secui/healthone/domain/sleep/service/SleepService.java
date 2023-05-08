package com.secui.healthone.domain.sleep.service;

import com.secui.healthone.domain.sleep.dto.SleepInsertDto;
import com.secui.healthone.domain.sleep.dto.SleepResDto;
import com.secui.healthone.domain.sleep.dto.SleepUpdateDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface SleepService {

    List<SleepResDto> getSleepData(String date);

    void addSleepInfo(SleepInsertDto sleepInfoReqDto);

    void updateSleepInfo(SleepUpdateDto sleepInfoReqDto);

    void deleteSleepInfo(Integer no);

    Slice<SleepResDto> getSleepDataList(Integer userNo, Pageable pageable);
}
