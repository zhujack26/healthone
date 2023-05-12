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

    @Override
    @Transactional
    public HealthInfoDto updateHealthInfo(HealthInfoDto healthInfoDto) {
        Optional<HealthInfo> healthInfo = healthInfoRepository.findByUserNo(healthInfoDto.getUserNo());
        if(healthInfo.isEmpty()) {
            HealthInfo result = healthInfoRepository.save(HealthInfoDtoMapper.INSTANCE.dtoToEntity(healthInfoDto));
            return HealthInfoDtoMapper.INSTANCE.entityToDto(result);
        } else {
            healthInfo.get().update(healthInfoDto);
            return HealthInfoDtoMapper.INSTANCE.entityToDto(healthInfo.get());
        }
    }

    @Override
    @Transactional
    public void deleteHealthInfo(Integer no) {
        healthInfoRepository.deleteById(no);
    }
}