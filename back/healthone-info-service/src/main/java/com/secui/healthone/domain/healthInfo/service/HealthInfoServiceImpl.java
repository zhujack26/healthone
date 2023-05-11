package com.secui.healthone.domain.healthInfo.service;

import com.secui.healthone.domain.healthInfo.dto.HealthInfoDto;
import com.secui.healthone.domain.healthInfo.dto.HealthInfoDtoMapper;
import com.secui.healthone.domain.healthInfo.entity.HealthInfo;
import com.secui.healthone.domain.healthInfo.repository.HealthInfoRepository;
import com.secui.healthone.domain.user.repository.UserRepository;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthInfoServiceImpl implements HealthInfoService {

    private final HealthInfoRepository healthInfoRepository;
    private final UserRepository userRepository;

    @Override
    public HealthInfoDto getHealthInfo(Integer no, Integer userNo) {
        HealthInfo healthInfo = healthInfoRepository.findByNoAndUserNo(no, userNo).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        return HealthInfoDtoMapper.INSTANCE.entityToDto(healthInfo);
    }

    @Override
    public HealthInfoDto addHealthInfo(HealthInfoDto healthInfoDto) {
        HealthInfo result = healthInfoRepository.save(HealthInfoDtoMapper.INSTANCE.dtoToEntity(healthInfoDto));
        return HealthInfoDtoMapper.INSTANCE.entityToDto(result);
    }

    @Override
    public HealthInfoDto updateHealthInfo(HealthInfoDto healthInfoDto) {
//        userRepository.findById(healthInfoDto.getUserNo()).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        HealthInfo healthInfo = healthInfoRepository.findById(healthInfoDto.getNo()).orElseThrow();
        if (healthInfoDto.getHeight() != null) {
            healthInfo.setHeight(healthInfoDto.getHeight());
        }
        if (healthInfoDto.getBirthdate() != null) {
            healthInfo.setBirthdate(healthInfoDto.getBirthdate());
        }
        if (healthInfoDto.getSleepTime() != null) {
            healthInfo.setSleepTime(healthInfoDto.getSleepTime());
        }
        if (healthInfoDto.getSleepGoal() != null) {
            healthInfo.setSleepGoal(healthInfoDto.getSleepGoal());
        }
        if (healthInfoDto.getWeight() != null) {
            healthInfo.setWeight(healthInfoDto.getWeight());
        }
        if (healthInfoDto.getStepGoal() != null) {
            healthInfo.setStepGoal(healthInfoDto.getStepGoal());
        }
        if (healthInfoDto.getWorkRate() != null) {
            healthInfo.setWorkRate(healthInfoDto.getWorkRate());
        }
        if (healthInfoDto.getWakeUpTime() != null) {
            healthInfo.setWakeUpTime(healthInfoDto.getWakeUpTime());
        }
        healthInfoRepository.save(healthInfo);
        return HealthInfoDtoMapper.INSTANCE.entityToDto(healthInfo);
    }

    @Override
    public void deleteHealthInfo(String no) {
        healthInfoRepository.deleteById(Integer.parseInt(no));
    }
}