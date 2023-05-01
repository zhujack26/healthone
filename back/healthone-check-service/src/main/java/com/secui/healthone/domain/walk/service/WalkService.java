package com.secui.healthone.domain.walk.service;

import com.secui.healthone.domain.walk.entity.Walk;

import java.util.List;

public interface WalkService {

    List<Walk> getWalkEntitiesForSevenDays(String date);

}
