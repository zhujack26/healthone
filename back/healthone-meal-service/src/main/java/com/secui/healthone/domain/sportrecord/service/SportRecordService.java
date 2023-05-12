package com.secui.healthone.domain.sportrecord.service;

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

    public List<SportRecordResDto> getSportRecordList(String date, Integer userNo) {
        StringDateTrans dateTrans = new StringDateTrans(date);
        List<SportRecord> result = sportRecordRepository.findByCreateTimeBetweenAndUserNo(dateTrans.getStartDateTime(), dateTrans.getEndDateTime(), userNo);
        return SportRecordDtoMapper.INSTANCE.entityToResListDto(result);
    }

    // 등록
    @Transactional
    public SportRecordResDto insertSportRecord(SportRecordReqDto reqDto) {
        SportRecord saveResult = sportRecordRepository.save(SportRecordDtoMapper.INSTANCE.reqDtoToEntity(reqDto));
        return SportRecordDtoMapper.INSTANCE.entityToResDto(saveResult);
    }

    // 수정
    @Transactional
    public SportRecordResDto updateSportRecord(SportRecordReqDto reqDto) {
        SportRecord sportRecord = sportRecordRepository.findByNoAndUserNo(reqDto.getNo(), reqDto.getUserNo())
                .orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        sportRecord.update(reqDto);
        return SportRecordDtoMapper.INSTANCE.entityToResDto(sportRecord);
    }

    @Transactional
    public void deleteSportRecord(Integer no, Integer userNo) {
        SportRecord sportRecord = sportRecordRepository.findByNoAndUserNo(no, userNo)
                .orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        sportRecordRepository.delete(sportRecord);
    }
}
