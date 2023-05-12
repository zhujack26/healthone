package com.secui.healthone.domain.heartRate.service;

import com.secui.healthone.domain.heartRate.dto.HeartRateInsertDto;
import com.secui.healthone.domain.heartRate.dto.HeartRateDtoMapper;
import com.secui.healthone.domain.heartRate.dto.HeartRateResDto;
import com.secui.healthone.domain.heartRate.entity.HeartRate;
import com.secui.healthone.domain.heartRate.repository.HeartRateRepository;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        HeartRate result = heartRateRepository.save(HeartRateDtoMapper.INSTANCE.insertDtoToEntity(heartRateInsertDto));
        return new HeartRateResDto(result);
    }

    @Override
    @Transactional
    public void deleteHeartRateInfo(Integer no, Integer userNo) {
        heartRateRepository.findByNoAndUserNo(no, userNo).orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        heartRateRepository.deleteByNoAndUserNo(no, userNo);
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
}
