package com.secui.healthone.domain.walk.service;

import com.secui.healthone.domain.walk.dto.WalkResDto;

import java.util.List;

public interface WalkService {

    List<WalkResDto> getWalkEntitiesForSevenDays(String date);

    List<WalkResDto> getDetailedWalkInfo(String date);

}
