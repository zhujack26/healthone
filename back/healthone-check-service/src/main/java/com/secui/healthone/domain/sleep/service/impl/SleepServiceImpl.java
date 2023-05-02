package com.secui.healthone.domain.sleep.service.impl;

import com.secui.healthone.domain.sleep.dto.SleepInsertDto;
import com.secui.healthone.domain.sleep.dto.SleepUpdateDto;
import com.secui.healthone.domain.sleep.entity.Sleep;
import com.secui.healthone.domain.sleep.repository.SleepRepository;
import com.secui.healthone.domain.sleep.service.SleepService;
import com.secui.healthone.domain.user.entity.User;
import com.secui.healthone.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SleepServiceImpl implements SleepService {

    @Autowired
    private SleepRepository sleepRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Sleep> getSleepData(String date) {
        //        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
        Optional<User> optionalUser = userRepository.findById(1);
        if (optionalUser.isEmpty()) {
        }
        User user = optionalUser.get();
        return sleepRepository.findAllByUserAndSleepCreatetimeLike(user, date);
    }

    @Override
    public void addSleepInfo(SleepInsertDto sleepInfoReqDto) {
        //        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
        Optional<User> optionalUser = userRepository.findById(sleepInfoReqDto.getUserNo());
        if (optionalUser.isEmpty()) {
        }
        User user = optionalUser.get();
        LocalDateTime sleepCreatetime = typeConverter(sleepInfoReqDto.getSleepCreatetime());
        LocalDateTime sleepStartSleepTime = typeConverter(sleepInfoReqDto.getSleepStartSleepTime());
        LocalDateTime sleepEndSleepTime = typeConverter(sleepInfoReqDto.getSleepEndSleepTime());
        Sleep sleep = Sleep.builder()
                .user(user)
                .sleepCreatetime(sleepCreatetime)
                .sleepStartSleepTime(sleepStartSleepTime)
                .sleepEndSleepTime(sleepEndSleepTime)
                .build();
        sleepRepository.save(sleep);
    }

    @Override
    public void updateSleepInfo(SleepUpdateDto sleepUpdateDto) {
        int sleepNo = Integer.parseInt(sleepUpdateDto.getNo());
        Optional<Sleep> optionalSleep = sleepRepository.findById(sleepNo);
        if (optionalSleep.isEmpty()) {
        }
        Sleep sleep = optionalSleep.get();

        if (sleepUpdateDto.getSleepCreatetime() != null) {
            LocalDateTime sleepCreatetime = typeConverter(sleepUpdateDto.getSleepCreatetime());
            sleep.setSleepCreatetime(sleepCreatetime);
        }
        if (sleepUpdateDto.getSleepStartSleepTime() != null) {
            LocalDateTime sleepStartSleepTime = typeConverter(sleepUpdateDto.getSleepStartSleepTime());
            sleep.setSleepStartSleepTime(sleepStartSleepTime);
        }
        if (sleepUpdateDto.getSleepEndSleepTime() != null) {
            LocalDateTime sleepEndSleepTime = typeConverter(sleepUpdateDto.getSleepEndSleepTime());
            sleep.setSleepEndSleepTime(sleepEndSleepTime);
        }
        sleepRepository.save(sleep);
    }

    @Override
    public void deleteSleepInfo(String no) {
        int sleepNo = Integer.parseInt(no);
        sleepRepository.deleteById(sleepNo);
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
