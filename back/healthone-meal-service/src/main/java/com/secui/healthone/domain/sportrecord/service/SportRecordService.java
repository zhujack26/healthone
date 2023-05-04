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

    // 등록, 수정
    @Transactional
    public SportRecordResDto insertSportRecord(SportRecordReqDto reqDto) {
//        Sport sport = null;
//        CustomSport customSport = null;
//        if (reqDto.getSportNo() != null){
//            sport = sportRepository.findById(reqDto.getSportNo())
//                    .orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
//            reqDto.setSportNo(sport.getNo());
//        } else if (reqDto.getCustomSportNo()!= null){
//            customSport = customSportRepository.findByNoAndUserNo(reqDto.getCustomSportNo(), reqDto.getUserNo())
//                    .orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
//            reqDto.setCustomSportNo(customSport.getNo());
//        }
//        sportRecordRepository.save(SportRecordDtoMapper.INSTANCE.reqDtoToEntity(reqDto, sport, customSport));
//        return SportRecordDtoMapper.INSTANCE.entityToResDto(SportRecordDtoMapper.INSTANCE.reqDtoToEntity(reqDto, sport, customSport));
        SportRecord saveResult = sportRecordRepository.save(SportRecordDtoMapper.INSTANCE.reqDtoToEntity(reqDto));
        return SportRecordDtoMapper.INSTANCE.entityToResDto(saveResult);
    }

    @Transactional
    public void deleteSportRecord(Integer no) {
        SportRecord sportRecord = sportRecordRepository.findById(no)
                .orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        sportRecordRepository.delete(sportRecord);
    }
}
