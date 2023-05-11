package com.secui.healthone.domain.healthAdvice.service;

import com.secui.healthone.domain.healthAdvice.dto.HealthAdviceDto;
import com.secui.healthone.domain.healthInfo.entity.HealthInfo;
import com.secui.healthone.domain.healthInfo.repository.HealthInfoRepository;
import com.secui.healthone.domain.healthStat.entity.HealthStat;
import com.secui.healthone.domain.healthStat.repository.HealthStatRepository;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import com.secui.healthone.global.util.StringDateTrans;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthAdviceService {
    private final HealthStatRepository healthStatRepository;

    public HealthAdviceDto getHealthAdvice(String date, Integer userNo) {
        StringDateTrans trans = new StringDateTrans(date);
        HealthStat healthStat = healthStatRepository.findByUserNoAndCreatetimeBetween(userNo, trans.getStartDateTime(), trans.getEndDateTime())
                .orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));

        return null;
    }
//    public HealthAdviceDto getHealthAdvice(HealthAdviceGetReqDto healthAdviceGetReqDto) {
////        User user = userRepository.findById(getHealthAdviceReqDto.getUserNo()).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
//        StringDateTrans trans = new StringDateTrans(healthAdviceGetReqDto.getDate());
//        HealthAdvice healthAdviceDto = healthAdviceRepository.findByCreatetimeAndUserNo(trans.getStartDateTime(), healthAdviceGetReqDto.getUserNo()).orElseThrow();
//        return HealthAdviceDtoMapper.INSTANCE.entityToDto(healthAdviceDto);
//    }

//    @Override
//    public void deleteHealthAdvice(HealthAdviceDeleteReqDto healthAdviceDeleteReqDto) {
////        User user = userRepository.findById(deleteHealthAdviceReqDto.getUserNo()).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
//        healthAdviceRepository.deleteById(healthAdviceDeleteReqDto.getNo());
//    }

}
