package com.secui.healthone.domain.healthAdvice.service;

import com.secui.healthone.domain.healthAdvice.dto.GetHealthAdviceReqDto;
import com.secui.healthone.domain.healthAdvice.dto.HealthAdviceDeleteReqDto;
import com.secui.healthone.domain.healthAdvice.dto.HealthAdviceDto;
import com.secui.healthone.domain.healthAdvice.dto.HealthAdviceDtoMapper;
import com.secui.healthone.domain.healthAdvice.entity.HealthAdvice;
import com.secui.healthone.domain.healthAdvice.repository.HealthAdviceRepository;
import com.secui.healthone.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthAdviceServiceImpl implements HealthAdviceService {

    private final HealthAdviceRepository healthAdviceRepository;
    private final UserRepository userRepository;

    @Override
    public List<HealthAdviceDto> getHealthAdvice(GetHealthAdviceReqDto getHealthAdviceReqDto) {
//        User user = userRepository.findById(getHealthAdviceReqDto.getUserNo()).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        LocalDateTime localDateTime = typeConverter(getHealthAdviceReqDto.getDate());
        List<HealthAdvice> healthAdviceResDtoList = healthAdviceRepository.findByCreatetimeAndUserNo(localDateTime, getHealthAdviceReqDto.getUserNo());
        return HealthAdviceDtoMapper.INSTANCE.entityListToDtoList(healthAdviceResDtoList);
    }

    @Override
    public void deleteHealthAdvice(HealthAdviceDeleteReqDto healthAdviceDeleteReqDto) {
//        User user = userRepository.findById(healthAdviceDeleteReqDto.getUserNo()).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        healthAdviceRepository.deleteById(healthAdviceDeleteReqDto.getNo());
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
