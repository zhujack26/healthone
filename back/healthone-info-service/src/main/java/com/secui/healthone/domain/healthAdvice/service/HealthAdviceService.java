package com.secui.healthone.domain.healthAdvice.service;

import com.secui.healthone.domain.healthAdvice.dto.GetHealthAdviceReqDto;
import com.secui.healthone.domain.healthAdvice.dto.HealthAdviceDeleteReqDto;
import com.secui.healthone.domain.healthAdvice.dto.HealthAdviceDto;

import java.util.List;

public interface HealthAdviceService {


    List<HealthAdviceDto> getHealthAdvice(GetHealthAdviceReqDto getHealthAdviceReqDto);

    void deleteHealthAdvice(HealthAdviceDeleteReqDto healthAdviceDeleteReqDto);
}
