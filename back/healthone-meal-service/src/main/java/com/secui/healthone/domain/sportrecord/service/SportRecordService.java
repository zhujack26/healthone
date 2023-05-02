package com.secui.healthone.domain.sportrecord.service;

import com.secui.healthone.domain.sport.entity.CustomSport;
import com.secui.healthone.domain.sport.entity.Sport;
import com.secui.healthone.domain.sport.repository.CustomSportRepository;
import com.secui.healthone.domain.sport.repository.SportRepository;
import com.secui.healthone.domain.sportrecord.dto.SportRecordDtoMapper;
import com.secui.healthone.domain.sportrecord.dto.SportRecordReqDto;
import com.secui.healthone.domain.sportrecord.dto.SportRecordResDto;
import com.secui.healthone.domain.sportrecord.entity.SportRecord;
import com.secui.healthone.domain.sportrecord.repository.SportRecordRepository;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    // 등록, 수정 (테스트 미수행)
    @Transactional
    public SportRecordResDto insertSportRecord(SportRecordReqDto reqDto) {
        Sport sport = null;
        CustomSport customSport = null;
        if (reqDto.getSportNo() != null){
            sport = sportRepository.findById(reqDto.getSportNo())
                    .orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
            reqDto.setSportNo(sport.getNo());
        } else if (reqDto.getCustomSportNo()!= null){
            customSport = customSportRepository.findByNoAndUserNo(reqDto.getCustomSportNo(), reqDto.getUserNo())
                    .orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
            reqDto.setCustomSportNo(customSport.getNo());
        }
        sportRecordRepository.save(sportRecordDtoMapper.reqDtoToEntity(reqDto));
        return sportRecordDtoMapper.entityToResDto(sportRecordDtoMapper.reqDtoToEntity(reqDto));
    }

    @Transactional
    public void deleteSportRecord(Integer no) {
        SportRecord sportRecord = sportRecordRepository.findById(no)
                .orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        sportRecordRepository.delete(sportRecord);
    }
}
