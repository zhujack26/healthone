package com.secui.healthone.domain.activity.service;

import com.secui.healthone.domain.activity.dto.ActivityResDto;
import com.secui.healthone.domain.sportrecord.entity.SportRecord;
import com.secui.healthone.domain.sportrecord.repository.SportRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final SportRecordRepository sportRecordRepository;

    @Transactional
    public ActivityResDto getActivityData(Integer userNo) {
        List<SportRecord> sportRecordList = sportRecordRepository.findAllByUserNo(userNo);
        int totalConsumeCalorie = 0;
        int bestConsumeCalorie = 0;
        float totalWorkTime = 0f;
        float bestWorkTime = 0f;
        for (SportRecord sportRecord : sportRecordList) {
            int consumeCalorie = sportRecord.getConsumeCalorie();
            float workTime = sportRecord.getWorkTime();
            bestConsumeCalorie = Math.max(bestConsumeCalorie, consumeCalorie);
            bestWorkTime = Math.max(bestWorkTime, workTime);
            totalConsumeCalorie += consumeCalorie;
            totalWorkTime += workTime;
        }
        return ActivityResDto.builder()
                .totalActivityTime((int)totalWorkTime)
                .bestActivityTime((int)bestWorkTime)
                .totalConsumeCalorie(totalConsumeCalorie)
                .bestConsumeCalorie(bestConsumeCalorie)
                .build();
    }


}
