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

}
