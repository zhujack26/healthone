package com.secui.healthone.domain.sportrecord.service;

import com.secui.healthone.domain.meal.dto.MealResDto;
import com.secui.healthone.domain.meal.entity.Meal;
import com.secui.healthone.domain.sport.repository.CustomSportRepository;
import com.secui.healthone.domain.sport.repository.SportRepository;
import com.secui.healthone.domain.sportrecord.dto.SportRecordDtoMapper;
import com.secui.healthone.domain.sportrecord.dto.SportRecordResDto;
import com.secui.healthone.domain.sportrecord.entity.SportRecord;
import com.secui.healthone.domain.sportrecord.repository.SportRecordRepository;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SportRecordService {
    private final SportRecordRepository sportRecordRepository;
    private final SportRepository sportRepository;
    private final CustomSportRepository customSportRepository;
    private final SportRecordDtoMapper sportRecordDtoMapper;

    public List<SportRecordResDto> getSportRecordList(String date, Integer userNo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime startDateTime = LocalDate.parse(date, formatter).atStartOfDay();
        LocalDateTime endDateTime = startDateTime.plusDays(1).minusSeconds(1);
        List<SportRecord> result = sportRecordRepository.findByCreateTimeBetweenAndUserNo(startDateTime, endDateTime, userNo);
        return sportRecordDtoMapper.entityToResListDto(result);
    }

}
