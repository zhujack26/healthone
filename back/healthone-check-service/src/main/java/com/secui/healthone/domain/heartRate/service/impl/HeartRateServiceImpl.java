package com.secui.healthone.domain.heartRate.service.impl;

import com.secui.healthone.domain.heartRate.dto.AddHeartRateInfoReqDto;
import com.secui.healthone.domain.heartRate.entity.HeartRate;
import com.secui.healthone.domain.heartRate.repository.HeartRateRepository;
import com.secui.healthone.domain.heartRate.service.HeartRateService;
import com.secui.healthone.domain.user.entity.User;
import com.secui.healthone.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class HeartRateServiceImpl implements HeartRateService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HeartRateRepository heartRateRepository;

    @Override
    public void addHeartRateInfo(AddHeartRateInfoReqDto addHeartRateInfoReqDto) {
        //        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
        Optional<User> optionalUser = userRepository.findById(addHeartRateInfoReqDto.getUserNo());
        if (optionalUser.isEmpty()) {
        }
        User user = optionalUser.get();
        HeartRate heartRate = HeartRate.builder()
                .user(user)
                .heartRateCount(addHeartRateInfoReqDto.getHeartRateCount())
                .heartRateCreatetime(addHeartRateInfoReqDto.getCreatetime())
                .build();
        heartRateRepository.save(heartRate);
    }

    @Override
    public void deleteHeartRateInfo(String no) {
        int heartRateNo = Integer.parseInt(no);
        heartRateRepository.deleteById(heartRateNo);
    }

    @Override
    public List<HeartRate> getWeeklyHeartRate(String dateTime) {
        Optional<User> optionalUser = userRepository.findById(1);
//        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
        if (optionalUser.isEmpty()) {
        }
        User user = optionalUser.get();
        LocalDateTime endDateTime = typeConverter(dateTime);
        LocalDateTime startDateTime = endDateTime.minusDays(6).withHour(0).withMinute(0).withSecond(0).withNano(0);
        List<HeartRate> heartRateList = heartRateRepository.findAllByUserAndHeartRateCreatetimeBetween(user, startDateTime, endDateTime);
        if (heartRateList.isEmpty()) {
        }
        return heartRateList;
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
