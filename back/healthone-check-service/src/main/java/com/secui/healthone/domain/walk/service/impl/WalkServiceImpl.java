package com.secui.healthone.domain.walk.service.impl;

import com.secui.healthone.domain.walk.entity.User;
import com.secui.healthone.domain.walk.entity.Walk;
import com.secui.healthone.domain.walk.repository.UserRepository;
import com.secui.healthone.domain.walk.repository.WalkRepository;
import com.secui.healthone.domain.walk.service.WalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalkServiceImpl implements WalkService {

    @Autowired
    private WalkRepository walkRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Walk> getWalkEntitiesForSevenDays(String dateTime) {
        Optional<User> optionalUser = userRepository.findById(1);
//        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
        if (optionalUser.isEmpty()) {
        }
        User user = optionalUser.get();
        LocalDateTime endDateTime = typeConverter(dateTime);
        LocalDateTime startDateTime = endDateTime.minusDays(6).withHour(0).withMinute(0).withSecond(0).withNano(0);
        List<Walk> walkList = walkRepository.findAllByUserAndWalkCreatetimeBetween(user, startDateTime, endDateTime);
        if (walkList.isEmpty()) {
        }
        return walkList;
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
