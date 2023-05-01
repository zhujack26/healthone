package com.secui.healthone.domain.stress.service.impl;

import com.secui.healthone.domain.stress.dto.UploadUserStressInfoRequestDto;
import com.secui.healthone.domain.stress.entity.Stress;
import com.secui.healthone.domain.stress.repository.StressRepository;
import com.secui.healthone.domain.stress.service.StressService;
import com.secui.healthone.global.entity.User;
import com.secui.healthone.global.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StressServiceImpl implements StressService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StressRepository stressRepository;

    @Override
    public List<Stress> getStressList(String date, int page) {
//        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
        Optional<User> optionalUser = userRepository.findById(1);

        if (optionalUser.isEmpty()) {
        }
        User user = optionalUser.get();
        log.info("=========================================================================");
        System.out.println(user.toString());
        log.info("=========================================================================");
        LocalDateTime localDateTime = typeConverter(date).plusNanos(1000L);
        log.info(localDateTime.toString());
        List<Stress> stressList = stressRepository.findAllByUserAndStressCreatetimeBeforeOrderByStressCreatetime(user, localDateTime);
        log.info("stressList.size = " + stressList.size());
        if(stressList.isEmpty()){
            return null;
        }
        int size = stressList.size() > 7 ? 7 : stressList.size() - 1;
        return stressList.subList(0, size);
    }

    @Override
    public void uploadUserStressInfo(UploadUserStressInfoRequestDto stressInfoDto) {
        Optional<User> optionalUser = userRepository.findById(stressInfoDto.getUserNo());
        if (optionalUser.isEmpty()) {
        }
        User user = optionalUser.get();
        Stress stress = Stress.builder()
                .user(user)
                .stressCreatetime(stressInfoDto.getCreateTime())
                .stressLevel(stressInfoDto.getLevel())
                .build();
        stressRepository.save(stress);
    }

    @Override
    public void deleteUserStressInfo(String no) {
        int stressNo = Integer.parseInt(no);
        stressRepository.deleteById(stressNo);
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
