package com.secui.healthone.domain.healthAdvice.service;

import com.secui.healthone.domain.healthAdvice.dto.HealthAdviceGetReqDto;
import com.secui.healthone.domain.healthAdvice.dto.HealthAdviceDeleteReqDto;
import com.secui.healthone.domain.healthAdvice.dto.HealthAdviceDto;
import com.secui.healthone.domain.healthAdvice.dto.HealthAdviceDtoMapper;
import com.secui.healthone.domain.healthAdvice.entity.HealthAdvice;
import com.secui.healthone.domain.healthAdvice.repository.HealthAdviceRepository;
import com.secui.healthone.domain.user.repository.UserRepository;
import com.secui.healthone.global.util.StringDateTrans;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthAdviceServiceImpl implements HealthAdviceService {

    private final HealthAdviceRepository healthAdviceRepository;
    private final UserRepository userRepository;

    @Override
    public HealthAdviceDto getHealthAdvice(HealthAdviceGetReqDto healthAdviceGetReqDto) {
//        User user = userRepository.findById(getHealthAdviceReqDto.getUserNo()).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        StringDateTrans trans = new StringDateTrans(healthAdviceGetReqDto.getDate());
        HealthAdvice healthAdviceDto = healthAdviceRepository.findByCreatetimeAndUserNo(trans.getStartDateTime(), healthAdviceGetReqDto.getUserNo()).orElseThrow();
        return HealthAdviceDtoMapper.INSTANCE.entityToDto(healthAdviceDto);
    }

    @Override
    public void deleteHealthAdvice(HealthAdviceDeleteReqDto healthAdviceDeleteReqDto) {
//        User user = userRepository.findById(deleteHealthAdviceReqDto.getUserNo()).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        healthAdviceRepository.deleteById(healthAdviceDeleteReqDto.getNo());
    }

}
