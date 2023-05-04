package com.secui.healthone.domain.walk.service;

import com.secui.healthone.domain.user.entity.User;
import com.secui.healthone.domain.walk.dto.WalkDtoMapper;
import com.secui.healthone.domain.walk.dto.WalkResDto;
import com.secui.healthone.domain.walk.entity.Walk;
import com.secui.healthone.domain.user.repository.UserRepository;
import com.secui.healthone.domain.walk.repository.WalkRepository;
import com.secui.healthone.domain.walk.service.WalkService;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalkServiceImpl implements WalkService {

    private final WalkRepository walkRepository;
    private final UserRepository userRepository;

    @Override
    public List<WalkResDto> getWalkEntitiesForSevenDays(String dateTime) {
        Optional<User> optionalUser = userRepository.findById(1);
//        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
        User user = optionalUser.orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        LocalDateTime endDateTime = typeConverter(dateTime);
        LocalDateTime startDateTime = endDateTime.minusDays(6).withHour(0).withMinute(0).withSecond(0).withNano(0);
        List<Walk> walkList = walkRepository.findAllByUserNoAndCreatetimeBetween(1, startDateTime, endDateTime);
        return WalkDtoMapper.INSTANCE.entityToResDto(walkList);
    }

    @Override
    public List<WalkResDto> getDetailedWalkInfo(String date) {
        Optional<User> optionalUser = userRepository.findById(1);
//        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
        User user = optionalUser.orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        LocalDateTime localDateTime = typeConverter(date);
        List<Walk> walkList = walkRepository.findAllByUserNoAndCreatetime(1, localDateTime);
        return WalkDtoMapper.INSTANCE.entityToResDto(walkList);
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
