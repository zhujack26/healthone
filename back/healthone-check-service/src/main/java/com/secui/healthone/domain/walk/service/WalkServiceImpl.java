package com.secui.healthone.domain.walk.service;

import com.secui.healthone.domain.walk.dto.WalkDtoMapper;
import com.secui.healthone.domain.walk.dto.WalkReqDto;
import com.secui.healthone.domain.walk.dto.WalkResDto;
import com.secui.healthone.domain.walk.entity.Walk;
import com.secui.healthone.domain.walk.repository.WalkRepository;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalkServiceImpl implements WalkService {

    private final WalkRepository walkRepository;

    @Override
    public List<WalkResDto> getWalkEntitiesForSevenDays(String dateTime) {
//        Optional<User> optionalUser = userRepository.findById(1);
//        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
//        User user = optionalUser.orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        LocalDateTime endDateTime = typeConverter(dateTime);
        LocalDateTime startDateTime = endDateTime.minusDays(6).withHour(0).withMinute(0).withSecond(0).withNano(0);
        List<Walk> walkList = walkRepository.findAllByUserNoAndCreatetimeBetween(1, startDateTime, endDateTime);
        return WalkDtoMapper.INSTANCE.entityToResDto(walkList);
    }

    @Override
    public List<WalkResDto> getDetailedWalkInfo(String date) {
//        Optional<User> optionalUser = userRepository.findById(1);
//        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
//        User user = optionalUser.orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        LocalDateTime localDateTime = typeConverter(date);
        List<Walk> walkList = walkRepository.findAllByUserNoAndCreatetime(1, localDateTime);
        return WalkDtoMapper.INSTANCE.entityToResDto(walkList);
    }

    @Override
    @Transactional
    public WalkResDto insertWalk(WalkReqDto reqDto) {
        Walk result = walkRepository.save(WalkDtoMapper.INSTANCE.reqDtoToEntity(reqDto));
        return WalkDtoMapper.INSTANCE.entityToResDto(result);
    }

    @Override
    public void deleteWalk(Integer no) {
        Walk getWalk = walkRepository.findById(no).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        walkRepository.delete(getWalk);
    }


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
