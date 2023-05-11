package com.secui.healthone.domain.healthStat.service;

import com.secui.healthone.domain.healthStat.dto.HealthStatDto;
import com.secui.healthone.domain.healthStat.dto.HealthStatDtoMapper;
import com.secui.healthone.domain.healthStat.entity.HealthStat;
import com.secui.healthone.domain.healthStat.repository.HealthStatRepository;
import com.secui.healthone.domain.user.repository.UserRepository;
import com.secui.healthone.global.util.StringDateTrans;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HealthStatServiceImpl implements HealthStatService {

    private final UserRepository userRepository;
    private final HealthStatRepository healthStatRepository;

    @Override
    public List<HealthStatDto> getHealthStat(String date) {
        StringDateTrans trans = new StringDateTrans(date);
        List<HealthStat> healthStatList = healthStatRepository.findByUserNoAndCreatetimeBetween(1, trans.getStartDateTime(), trans.getEndDateTime());
        return HealthStatDtoMapper.INSTANCE.entityListToDtoList(healthStatList);
    }

    @Override
    public HealthStatDto addHealthStat(HealthStatDto healthStatDto) {
//        User user = userRepository.findById(healthStatDto.getUserNo()).orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        HealthStat healthStat = HealthStatDtoMapper.INSTANCE.dtoToEntity(healthStatDto);
        healthStatRepository.save(healthStat);
        return HealthStatDtoMapper.INSTANCE.entityToDto(healthStat);
    }

    @Override
    public HealthStatDto updateHealthStat(HealthStatDto healthStatDto) {
//        User user = userRepository.findById(healthStatDto.getUserNo()).orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        HealthStat healthStat = healthStatRepository.findById(healthStatDto.getNo()).orElseThrow();
        if (healthStatDto.getCreatetime() != null) {
            LocalDateTime createtime = typeConverter(healthStatDto.getCreatetime());
            healthStat.setCreatetime(createtime);
        }
        if (healthStatDto.getHeight() != null) {
            healthStat.setHeight(healthStatDto.getHeight());
        }
        if (healthStatDto.getWeight() != null) {
            healthStat.setWeight(healthStatDto.getWeight());
        }
        if (healthStatDto.getBmi() != null) {
            healthStat.setBmi(healthStatDto.getBmi());
        }
        if (healthStatDto.getBodyFatPercentage() != null) {
            healthStat.setBodyFatPercentage(healthStatDto.getBodyFatPercentage());
        }
        if (healthStatDto.getSkeletalMuscleMass() != null) {
            healthStat.setSkeletalMuscleMass(healthStatDto.getSkeletalMuscleMass());
        }
        if (healthStatDto.getTg() != null) {
            healthStat.setTg(healthStatDto.getTg());
        }
        if (healthStatDto.getHdlCholesterol() != null) {
            healthStat.setHdlCholesterol(healthStatDto.getHdlCholesterol());
        }
        if (healthStatDto.getFbg() != null) {
            healthStat.setFbg(healthStatDto.getFbg());
        }
        if (healthStatDto.getBloodPressure() != null) {
            healthStat.setBloodPressure(healthStatDto.getBloodPressure());
        }
        if (healthStatDto.getWaistMeasurement() != null) {
            healthStat.setWaistMeasurement(healthStatDto.getWaistMeasurement());
        }
        healthStatRepository.save(healthStat);
        return HealthStatDtoMapper.INSTANCE.entityToDto(healthStat);
    }

    @Override
    public void deleteHealthStat(Integer no, Integer userNo) {
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