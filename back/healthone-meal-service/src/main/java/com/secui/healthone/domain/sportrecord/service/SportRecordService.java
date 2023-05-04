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
import com.secui.healthone.global.util.StringDateTrans;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        StringDateTrans dateTrans = new StringDateTrans(date);
        List<SportRecord> result = sportRecordRepository.findByCreateTimeBetweenAndUserNo(dateTrans.getStartDateTime(), dateTrans.getEndDateTime(), userNo);
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
        sportRecordRepository.save(sportRecordDtoMapper.reqDtoToEntity(reqDto, sport, customSport));
        return sportRecordDtoMapper.entityToResDto(sportRecordDtoMapper.reqDtoToEntity(reqDto, sport, customSport));
    }

    @Transactional
    public void deleteSportRecord(Integer no) {
        SportRecord sportRecord = sportRecordRepository.findById(no)
                .orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        sportRecordRepository.delete(sportRecord);
    }
}
