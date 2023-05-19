package com.secui.healthone.domain.sleep.service;

import com.secui.healthone.domain.sleep.dto.SleepDtoMapper;
import com.secui.healthone.domain.sleep.dto.SleepInsertDto;
import com.secui.healthone.domain.sleep.dto.SleepResDto;
import com.secui.healthone.domain.sleep.dto.SleepUpdateDto;
import com.secui.healthone.domain.sleep.repository.SleepRepository;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import com.secui.healthone.global.util.StringDateTrans;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.secui.healthone.global.util.StringDateTrans.stringDateTrans7Days;

@Service
@RequiredArgsConstructor
public class SleepServiceImpl implements SleepService {

    private final SleepRepository sleepRepository;

    // 수면 7일치 데이터 반환
    public List<SleepResDto> getSleepDataList(Integer userNo, String date) {
        StringDateTrans stringDateTrans = stringDateTrans7Days(date);
        return sleepRepository.findByCreateTimeBetweenAndUserNoOrderByCreateTimeDesc(
                        stringDateTrans.getStartDateTime(), stringDateTrans.getEndDateTime(),  userNo)
                .stream().map(SleepResDto::new).collect(Collectors.toList());
    }

    // 수면날짜 데이터 가져오기
    @Override
    public List<SleepResDto> getSleepData(String date, Integer userNo) {
        StringDateTrans dateTrans = new StringDateTrans(date);
        return SleepDtoMapper.INSTANCE.entityToResDto(sleepRepository.findByCreateTimeBetweenAndUserNo(dateTrans.getStartDateTime(), dateTrans.getEndDateTime(), userNo));
    }

    // 수면 정보 추가하기
    @Override
    @Transactional
    public SleepResDto addSleepInfo(SleepInsertDto sleepInfoReqDto) {
        return SleepDtoMapper.INSTANCE.entityToResDto(sleepRepository.save(SleepDtoMapper.INSTANCE.insertDtoToEntity(sleepInfoReqDto)));
    }
    
    // 수면정보 수정하기
    @Override
    @Transactional
    public SleepResDto updateSleepInfo(SleepUpdateDto sleepUpdateDto) {
        return SleepDtoMapper.INSTANCE.entityToResDto(sleepRepository.save(SleepDtoMapper.INSTANCE.updateDtoToEntity(sleepUpdateDto)));
    }

    // 수면정보 삭제하기
    @Override
    @Transactional
    public void deleteSleepInfo(Integer no, Integer userNo) {
        sleepRepository.findByNoAndUserNo(no, userNo).orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        sleepRepository.deleteByNoAndUserNo(no, userNo);
    }

}
