package com.secui.healthone.domain.heartRate.service.impl;

import com.secui.healthone.domain.heartRate.dto.AddHeartRateInfoReqDto;
import com.secui.healthone.domain.heartRate.dto.HeartRateDtoMapper;
import com.secui.healthone.domain.heartRate.dto.HeartRateResDto;
import com.secui.healthone.domain.heartRate.entity.HeartRate;
import com.secui.healthone.domain.heartRate.repository.HeartRateRepository;
import com.secui.healthone.domain.heartRate.service.HeartRateService;
import com.secui.healthone.domain.user.entity.User;
import com.secui.healthone.domain.user.repository.UserRepository;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class HeartRateServiceImpl implements HeartRateService {
    private final UserRepository userRepository;
    private final HeartRateRepository heartRateRepository;

    @Override
    public void addHeartRateInfo(AddHeartRateInfoReqDto addHeartRateInfoReqDto) {
//        Optional<User> optionalUser = userRepository.findById(addHeartRateInfoReqDto.getUserNo());
//        User user = optionalUser.orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        HeartRate heartRate = HeartRate.builder()
                .userNo(addHeartRateInfoReqDto.getUserNo())
                .count(addHeartRateInfoReqDto.getCount())
                .createtime(addHeartRateInfoReqDto.getCreatetime())
                .build();
        heartRateRepository.save(heartRate);
    }

    @Override
    public void deleteHeartRateInfo(String no) {
        int heartRateNo = Integer.parseInt(no);
        heartRateRepository.deleteById(heartRateNo);
    }

    @Override
    public List<HeartRateResDto> getWeeklyHeartRate(String dateTime) {
        Optional<User> optionalUser = userRepository.findById(1);
        User user = optionalUser.orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        LocalDateTime endDateTime = typeConverter(dateTime);
        LocalDateTime startDateTime = endDateTime.minusDays(6).withHour(0).withMinute(0).withSecond(0).withNano(0);
        List<HeartRate> heartRateList = heartRateRepository.findAllByUserNoAndCreatetimeBetween(1, startDateTime, endDateTime);
        return HeartRateDtoMapper.INSTANCE.entityToResDto(heartRateList);
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
