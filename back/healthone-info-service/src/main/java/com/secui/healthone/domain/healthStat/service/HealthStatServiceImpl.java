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
        healthStat.update(healthStatDto);
        return HealthStatDtoMapper.INSTANCE.entityToDto(healthStat);
    }

    @Override
    @Transactional
    public void deleteHealthStat(Integer no, Integer userNo) {
        healthStatRepository.findById(no).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        healthStatRepository.deleteByNoAndUserNo(no, userNo);
    }

}