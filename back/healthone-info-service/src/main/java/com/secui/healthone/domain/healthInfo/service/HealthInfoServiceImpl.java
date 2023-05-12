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
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HealthInfoServiceImpl implements HealthInfoService {

    private final HealthInfoRepository healthInfoRepository;
    private final UserRepository userRepository;

    @Override
    public HealthInfoDto getHealthInfo(Integer userNo) {
        HealthInfo healthInfo = healthInfoRepository.findByUserNo(userNo).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        return HealthInfoDtoMapper.INSTANCE.entityToDto(healthInfo);
    }

//    @Override
//    public HealthInfoDto addHealthInfo(HealthInfoDto healthInfoDto) {
//        Optional<HealthInfo> healthInfo = healthInfoRepository.findByUserNo(healthInfoDto.getUserNo());
//        if(healthInfo.isPresent()){
//            throw new RestApiException(CustomErrorCode.USER_402);
//        } else {
//            HealthInfo result = healthInfoRepository.save(HealthInfoDtoMapper.INSTANCE.dtoToEntity(healthInfoDto));
//            return HealthInfoDtoMapper.INSTANCE.entityToDto(result);
//        }
//    }

    @Override
    @Transactional
    public HealthInfoDto updateHealthInfo(HealthInfoDto healthInfoDto) {
        Optional<HealthInfo> healthInfo = healthInfoRepository.findById(healthInfoDto.getNo());
        if(healthInfo.isEmpty()) {
            healthInfoRepository.save(HealthInfoDtoMapper.INSTANCE.dtoToEntity(healthInfoDto));
        } else {
            healthInfo.get().update(healthInfoDto);
        }
        return HealthInfoDtoMapper.INSTANCE.entityToDto(healthInfo.orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100)));

//        if (healthInfoDto.getHeight() != null) healthInfo.setHeight(healthInfoDto.getHeight());
//        if (healthInfoDto.getBirthdate() != null) healthInfo.setBirthdate(healthInfoDto.getBirthdate());
//        if (healthInfoDto.getSleepTime() != null) healthInfo.setSleepTime(healthInfoDto.getSleepTime());
//        if (healthInfoDto.getSleepGoal() != null) healthInfo.setSleepGoal(healthInfoDto.getSleepGoal());
//        if (healthInfoDto.getWeight() != null) healthInfo.setWeight(healthInfoDto.getWeight());
//        if (healthInfoDto.getStepGoal() != null) healthInfo.setStepGoal(healthInfoDto.getStepGoal());
//        if (healthInfoDto.getWorkRate() != null) healthInfo.setWorkRate(healthInfoDto.getWorkRate());
//        if (healthInfoDto.getWakeUpTime() != null) healthInfo.setWakeUpTime(healthInfoDto.getWakeUpTime());
//        healthInfoRepository.save(healthInfo);
    }

    @Override
    @Transactional
    public void deleteHealthInfo(Integer no) {
        healthInfoRepository.deleteById(no);
    }
}