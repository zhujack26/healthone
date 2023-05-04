//package com.secui.healthone.domain.auth.service.impl;
//
//import com.secui.healthone.domain.auth.dto.SignUpRequestDto;
//import com.secui.healthone.domain.auth.entity.HealthInfo;
//import com.secui.healthone.domain.auth.entity.User;
//import com.secui.healthone.domain.auth.repository.HealthInfoRepository;
//import com.secui.healthone.domain.auth.repository.UserRepository;
//import com.secui.healthone.domain.auth.service.AuthService;
//import com.secui.healthone.global.config.security.jwt.JwtTokenProvider;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletResponse;
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class AuthServiceImpl implements AuthService {
//
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private HealthInfoRepository healthInfoRepository;
//
//    @Override
//    public boolean isSignUp(String email) {
//        Optional<User> optionalUser = userRepository.findByUserEmail(email);
//        return optionalUser.isEmpty();
//    }
//
//    @Override
//    public void signUp(SignUpRequestDto signUpRequestDto) {
//        User user = User.builder()
//                .userActive(true)
//                .userId(signUpRequestDto.getEmail().split("@")[0])
//                .userCreatetime(LocalDateTime.now())
//                .userEmail(signUpRequestDto.getEmail())
//                .userPlatformType(signUpRequestDto.getPlatformType())
//                .userProfilePicture(signUpRequestDto.getProfilePicture())
//                .build();
//        userRepository.save(user);
//
//        HealthInfo healthInfo = HealthInfo.builder()
//                .healthInfoWorkRate(signUpRequestDto.getWorkRate())
//                .healthInfoBirthDate(signUpRequestDto.getBirthdate())
//                .healthInfoGender(signUpRequestDto.isGender())
//                .healthInfoHeight(signUpRequestDto.getHeight())
//                .healthInfoWeight(signUpRequestDto.getWeight())
//                .healthInfoWorkRate(signUpRequestDto.getWorkRate())
//                .healthInfoSleepGoal(signUpRequestDto.getSleepGoal())
//                .healthInfoStepGoal(signUpRequestDto.getStepGoal())
//                .build();
//        healthInfoRepository.save(healthInfo);
//
//    }
//
//    @Override
//    public void signIn(HttpServletResponse response, String email) {
//        String accessToken = jwtTokenProvider.createToken(email, "access");
//        String refreshToken = jwtTokenProvider.createToken(email, "refresh");
//        Cookie cookie = new Cookie("refresh_token", refreshToken);
//        cookie.setPath("/");
//        cookie.setHttpOnly(true);
//        response.addCookie(cookie);
//        response.setContentType("application/json;charset=UTF-8");
//        response.setHeader("Authorization", "Bearer" + accessToken);
//    }
//}
