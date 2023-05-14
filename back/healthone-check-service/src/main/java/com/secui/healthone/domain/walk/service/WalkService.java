package com.secui.healthone.domain.walk.service;

import com.secui.healthone.domain.walk.dto.WalkReqDto;
import com.secui.healthone.domain.walk.dto.WalkResDto;

import java.util.List;

public interface WalkService {
    List<WalkResDto> getWalkEntitiesForSevenDays(String date, Integer userNo);
    List<WalkResDto> getDetailedWalkInfo(String date, Integer userNo);
    WalkResDto insertWalk(WalkReqDto walkReqDto);
    void deleteWalk(Integer no, Integer userNo);
}
