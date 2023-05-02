package com.secui.healthone.domain.sport.service;

import com.secui.healthone.domain.sport.dto.CustomSportMapper;
import com.secui.healthone.domain.sport.dto.CustomSportResDto;
import com.secui.healthone.domain.sport.dto.SportResDto;
import com.secui.healthone.domain.sport.entity.CustomSport;
import com.secui.healthone.domain.sport.entity.Sport;
import com.secui.healthone.domain.sport.repository.CustomSportRepository;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomSportService {
    private final CustomSportRepository customSportRepository;
    private final CustomSportMapper customSportMapper;

    // 사용자 운동데이터 검색 (리스트)
    public List<CustomSportResDto> searchCustomSport(Integer userNo, String name) {
        List<CustomSport> result = customSportRepository.findByUserNoAndNameContaining(userNo, name);
        return customSportMapper.customSportToCustomSportResListDto(result);
    }

    // 운동 데이터 단건 조회
    public SportResDto getSport(Integer no) {
//        Optional<Sport> result = customSportRepository.findById(no);
//        return customSportMapper.sportToSportResDto(result.orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100)));
        return null;
    }
}
