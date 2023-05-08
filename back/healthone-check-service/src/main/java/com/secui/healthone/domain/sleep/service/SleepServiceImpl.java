package com.secui.healthone.domain.sleep.service;

import com.secui.healthone.domain.sleep.dto.SleepDtoMapper;
import com.secui.healthone.domain.sleep.dto.SleepInsertDto;
import com.secui.healthone.domain.sleep.dto.SleepResDto;
import com.secui.healthone.domain.sleep.dto.SleepUpdateDto;
import com.secui.healthone.domain.sleep.entity.Sleep;
import com.secui.healthone.domain.sleep.repository.SleepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SleepServiceImpl implements SleepService {

    private final SleepRepository sleepRepository;

    // 수면날짜 데이터 가져오기
    @Override
    public List<SleepResDto> getSleepData(String date) {
        //        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
//        Optional<User> optionalUser = userRepository.findById(1);
//        User user = optionalUser.orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        return SleepDtoMapper.INSTANCE.entityToResDto(sleepRepository.findAllByUserNoAndCreateTimeLike(1, date));
    }

    // 수면 정보 추가하기
    @Override
    @Transactional
    public SleepResDto addSleepInfo(SleepInsertDto sleepInfoReqDto) {
        //        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
//        Optional<User> optionalUser = userRepository.findById(sleepInfoReqDto.getUserNo());
//        User user = optionalUser.orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));

//        LocalDateTime sleepCreatetime = typeConverter(sleepInfoReqDto.getCreateTime());
//        LocalDateTime sleepStartSleepTime = typeConverter(sleepInfoReqDto.getStartSleepTime());
//        LocalDateTime sleepEndSleepTime = typeConverter(sleepInfoReqDto.getEndSleepTime());

        Sleep sleep = Sleep.builder()
//                .userNo(user.getUserNo())
                .userNo(1)
                .createTime(sleepInfoReqDto.getCreateTime())
                .startSleepTime(sleepInfoReqDto.getStartSleepTime())
                .endSleepTime(sleepInfoReqDto.getEndSleepTime())
                .build();
        return SleepDtoMapper.INSTANCE.entityToResDto(sleepRepository.save(sleep));
    }
    
    // 수면정보 수정하기
    @Override
    @Transactional
    public void updateSleepInfo(SleepUpdateDto sleepUpdateDto) {
        int sleepNo = Integer.parseInt(sleepUpdateDto.getNo());
        Optional<Sleep> optionalSleep = sleepRepository.findById(sleepNo);
        if (optionalSleep.isEmpty()) {
        }
        Sleep sleep = optionalSleep.get();

        if (sleepUpdateDto.getCreateTime() != null) {
//            LocalDateTime sleepCreatetime = typeConverter(sleepUpdateDto.getCreateTime());
            sleep.setCreateTime(sleepUpdateDto.getCreateTime());
        }
        if (sleepUpdateDto.getStartSleepTime() != null) {
//            LocalDateTime sleepStartSleepTime = typeConverter(sleepUpdateDto.getStartSleepTime());
            sleep.setStartSleepTime(sleepUpdateDto.getStartSleepTime());
        }
        if (sleepUpdateDto.getEndSleepTime() != null) {
//            LocalDateTime sleepEndSleepTime = typeConverter(sleepUpdateDto.getEndSleepTime());
            sleep.setEndSleepTime(sleepUpdateDto.getEndSleepTime());
        }
        sleepRepository.save(sleep);
    }

    @Override
    public void deleteSleepInfo(Integer no) {
        sleepRepository.deleteById(no);
    }

    @Override
    public Slice<SleepResDto> getSleepDataList(Integer userNo, Pageable pageable) {
        return sleepRepository.findAllByUserNoOrderByCreateTimeDesc(userNo, pageable).map(SleepResDto::new);
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
