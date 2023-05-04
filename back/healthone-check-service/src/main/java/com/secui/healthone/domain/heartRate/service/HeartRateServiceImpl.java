package com.secui.healthone.domain.heartRate.service;

import com.secui.healthone.domain.heartRate.dto.HeartRateInsertDto;
import com.secui.healthone.domain.heartRate.dto.HeartRateDtoMapper;
import com.secui.healthone.domain.heartRate.dto.HeartRateResDto;
import com.secui.healthone.domain.heartRate.entity.HeartRate;
import com.secui.healthone.domain.heartRate.repository.HeartRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class HeartRateServiceImpl implements HeartRateService {
    private final HeartRateRepository heartRateRepository;

    @Override
    public Slice<HeartRateResDto> getHeartRateList(Integer userNo, Pageable pageable) {
        return heartRateRepository.findAllByUserNoOrderByCreateTimeDesc(userNo, pageable).map(HeartRateResDto::new);
    }

    @Override
    @Transactional
    public HeartRateResDto addHeartRateInfo(HeartRateInsertDto heartRateInsertDto) {
//        Optional<User> optionalUser = userRepository.findById(addHeartRateInfoReqDto.getUserNo());
//        User user = optionalUser.orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        log.info("heartRateInsertDto : {}", heartRateInsertDto);
        HeartRate result = heartRateRepository.save(HeartRateDtoMapper.INSTANCE.insertDtoToEntity(heartRateInsertDto));
        return new HeartRateResDto(result);
    }

    @Override
    @Transactional
    public void deleteHeartRateInfo(Integer no) {
        heartRateRepository.deleteById(no);
    }

//    @Override
//    public List<HeartRateResDto> getWeeklyHeartRate(String dateTime) {
////        Optional<User> optionalUser = userRepository.findById(1);
////        User user = optionalUser.orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
//        LocalDateTime endDateTime = typeConverter(dateTime);
//        LocalDateTime startDateTime = endDateTime.minusDays(6).withHour(0).withMinute(0).withSecond(0).withNano(0);
//        List<HeartRate> heartRateList = heartRateRepository.findAllByUserNoAndCreateTimeBetween(1, startDateTime, endDateTime);
//        return HeartRateDtoMapper.INSTANCE.entityToResDto(heartRateList);
//    }

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
