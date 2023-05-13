package com.secui.healthone.domain.healthStat.service;

import com.secui.healthone.domain.healthStat.dto.HealthStatDto;
import com.secui.healthone.domain.healthStat.dto.HealthStatDtoMapper;
import com.secui.healthone.domain.healthStat.entity.HealthStat;
import com.secui.healthone.domain.healthStat.repository.HealthStatRepository;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import com.secui.healthone.global.util.StringDateTrans;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.secui.healthone.global.util.StringDateTrans.stringDateTrans30Days;

@Service
@Slf4j
@RequiredArgsConstructor
public class HealthStatServiceImpl implements HealthStatService {

    private final HealthStatRepository healthStatRepository;

    @Override
    public List<HealthStatDto> getHealthStat(String date, Integer userNo) {
        StringDateTrans trans = stringDateTrans30Days(date);
        List<HealthStat> healthStat = healthStatRepository.findByUserNoAndCreateTimeBetweenOrderByCreateTime(userNo, trans.getStartDateTime(), trans.getEndDateTime());
        return HealthStatDtoMapper.INSTANCE.entityListToDtoList(healthStat);
    }

    @Override
    @Transactional
    public HealthStatDto addHealthStat(HealthStatDto healthStatDto) {
        StringDateTrans trans = new StringDateTrans(healthStatDto.getCreateTime());
        Optional<HealthStat> healthStat = healthStatRepository.findByUserNoAndCreateTimeBetween(healthStatDto.getUserNo(), trans.getStartDateTime(), trans.getEndDateTime());
        if(healthStat.isPresent()){
            throw new RestApiException(CustomErrorCode.STAT_400);
        } else {
            return HealthStatDtoMapper.INSTANCE.entityToDto(healthStatRepository.save(HealthStatDtoMapper.INSTANCE.dtoToEntity(healthStatDto)));
        }
    }

    @Override
    @Transactional
    public HealthStatDto updateHealthStat(HealthStatDto healthStatDto) {
        HealthStat healthStat = healthStatRepository.findById(healthStatDto.getNo()).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));

        Optional.ofNullable(healthStatDto.getCreateTime()).ifPresent(healthStat::setCreateTime);
        Optional.ofNullable(healthStatDto.getHeight()).ifPresent(healthStat::setHeight);
        Optional.ofNullable(healthStatDto.getWeight()).ifPresent(healthStat::setWeight);
        Optional.ofNullable(healthStatDto.getBmi()).ifPresent(healthStat::setBmi);
        Optional.ofNullable(healthStatDto.getBodyFatPercentage()).ifPresent(healthStat::setBodyFatPercentage);
        Optional.ofNullable(healthStatDto.getSkeletalMuscleMass()).ifPresent(healthStat::setSkeletalMuscleMass);
        Optional.ofNullable(healthStatDto.getTg()).ifPresent(healthStat::setTg);
        Optional.ofNullable(healthStatDto.getHdlCholesterol()).ifPresent(healthStat::setHdlCholesterol);
        Optional.ofNullable(healthStatDto.getFbg()).ifPresent(healthStat::setFbg);
        Optional.ofNullable(healthStatDto.getLowBloodPressure()).ifPresent(healthStat::setLowBloodPressure);
        Optional.ofNullable(healthStatDto.getHighBloodPressure()).ifPresent(healthStat::setHighBloodPressure);
        Optional.ofNullable(healthStatDto.getWaistMeasurement()).ifPresent(healthStat::setWaistMeasurement);

        return HealthStatDtoMapper.INSTANCE.entityToDto(healthStat);
    }

    @Override
    @Transactional
    public void deleteHealthStat(Integer no, Integer userNo) {
        HealthStat result = healthStatRepository.findById(no).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        healthStatRepository.deleteByNoAndUserNo(no, userNo);
    }

    public LocalDateTime typeConverter(String dateTime) {
        int year = Integer.parseInt(dateTime.substring(0, 4));
        int month = Integer.parseInt(dateTime.substring(5, 7));
        int dayOfMonth = Integer.parseInt(dateTime.substring(8, 10));
        if (dateTime.length() == 10) {
            return LocalDateTime.of(year, month, dayOfMonth, 0, 0);
        }
        int hour = Integer.parseInt(dateTime.substring(11, 13));
        int minute = Integer.parseInt(dateTime.substring(14, 16));
        LocalDateTime localDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
        int second = Integer.parseInt(dateTime.substring(17, 19));
        int nano = Integer.parseInt(dateTime.substring(20, 26)) * 1000;
        return localDateTime.withSecond(second).withNano(nano);
    }

}