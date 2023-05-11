package com.secui.healthone.domain.healthAdvice.service;

import com.secui.healthone.domain.healthAdvice.dto.HealthAdviceGetReqDto;
import com.secui.healthone.domain.healthAdvice.dto.HealthAdviceDeleteReqDto;
import com.secui.healthone.domain.healthAdvice.dto.HealthAdviceDto;

import java.util.List;

public interface HealthAdviceService {


    HealthAdviceDto getHealthAdvice(HealthAdviceGetReqDto healthAdviceGetReqDto);

    void deleteHealthAdvice(HealthAdviceDeleteReqDto healthAdviceDeleteReqDto);
}
