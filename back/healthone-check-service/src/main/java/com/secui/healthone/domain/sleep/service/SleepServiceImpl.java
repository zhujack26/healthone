package com.secui.healthone.domain.sleep.service;

import com.secui.healthone.domain.sleep.dto.SleepDtoMapper;
import com.secui.healthone.domain.sleep.dto.SleepInsertDto;
import com.secui.healthone.domain.sleep.dto.SleepResDto;
import com.secui.healthone.domain.sleep.dto.SleepUpdateDto;
import com.secui.healthone.domain.sleep.entity.Sleep;
import com.secui.healthone.domain.sleep.repository.SleepRepository;
import com.secui.healthone.domain.sleep.service.SleepService;
import com.secui.healthone.domain.user.entity.User;
import com.secui.healthone.domain.user.repository.UserRepository;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SleepServiceImpl implements SleepService {

    private final SleepRepository sleepRepository;

    private final UserRepository userRepository;

    @Override
    public List<SleepResDto> getSleepData(String date) {
        //        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
        Optional<User> optionalUser = userRepository.findById(1);
        User user = optionalUser.orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        return SleepDtoMapper.INSTANCE.entityToResDto(sleepRepository.findAllByUserNoAndCreatetimeLike(1, date));
    }

    @Override
    public void addSleepInfo(SleepInsertDto sleepInfoReqDto) {
        //        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
        Optional<User> optionalUser = userRepository.findById(sleepInfoReqDto.getUserNo());
        User user = optionalUser.orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));

        LocalDateTime sleepCreatetime = typeConverter(sleepInfoReqDto.getCreateTime());
        LocalDateTime sleepStartSleepTime = typeConverter(sleepInfoReqDto.getStartSleepTime());
        LocalDateTime sleepEndSleepTime = typeConverter(sleepInfoReqDto.getEndSleepTime());

        Sleep sleep = Sleep.builder()
                .userNo(user.getUserNo())
                .createtime(sleepCreatetime)
                .startSleepTime(sleepStartSleepTime)
                .endSleepTime(sleepEndSleepTime)
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

        if (sleepUpdateDto.getCreateTime() != null) {
            LocalDateTime sleepCreatetime = typeConverter(sleepUpdateDto.getCreateTime());
            sleep.setCreatetime(sleepCreatetime);
        }
        if (sleepUpdateDto.getStartSleepTime() != null) {
            LocalDateTime sleepStartSleepTime = typeConverter(sleepUpdateDto.getStartSleepTime());
            sleep.setStartSleepTime(sleepStartSleepTime);
        }
        if (sleepUpdateDto.getEndSleepTime() != null) {
            LocalDateTime sleepEndSleepTime = typeConverter(sleepUpdateDto.getEndSleepTime());
            sleep.setEndSleepTime(sleepEndSleepTime);
        }
        sleepRepository.save(sleep);
    }

    @Override
    public void deleteSleepInfo(Integer no) {
        sleepRepository.deleteById(no);
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
