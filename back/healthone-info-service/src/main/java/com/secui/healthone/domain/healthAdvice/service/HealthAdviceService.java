package com.secui.healthone.domain.healthAdvice.service;

import com.secui.healthone.domain.healthAdvice.dto.AdviceType;
import com.secui.healthone.domain.healthAdvice.dto.HealthAdviceDto;
import com.secui.healthone.domain.healthInfo.entity.HealthInfo;
import com.secui.healthone.domain.healthInfo.repository.HealthInfoRepository;
import com.secui.healthone.domain.healthStat.repository.HealthStatRepository;
import com.secui.healthone.domain.healthStat.entity.HealthStat;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthAdviceService {
    private final HealthStatRepository healthStatRepository;
    private final HealthInfoRepository healthInfoRepository;

    public HealthAdviceDto getHealthAdvice(Integer userNo) {
        HealthInfo healthInfo = healthInfoRepository.findByUserNo(userNo).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        HealthStat healthStat = healthStatRepository.findTopByUserNoOrderByCreateTimeDesc(userNo)
                .orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        float value = (healthStat.getWeight() / (healthStat.getHeight() * healthStat.getHeight())) * 100f;

        return HealthAdviceDto.builder()
                .userNo(healthStat.getUserNo()) // 회원정보
                .createtime(healthStat.getCreateTime()) // 건강조언 날짜
                .weight(getWeightAdvice(value)) // 체중조언
                .bmi(getBMIAdvice(value)) // bmi조언
                .fatPercentage(getFatPercentageAdvice(healthStat.getBodyFatPercentage(), healthInfo.getGender())) // 체지방조언
                .skeletalMuscleMass(getSkeletalMuscleAdvice(healthStat.getSkeletalMuscleMass(), healthStat.getWeight(), healthInfo.getGender()))// 골격근량 조언
                .bloodPressure(getBloodPressureAdvice(healthStat.getLowBloodPressure(), healthStat.getHighBloodPressure())) // 혈압 조언 (수정필요)
                .tg(getTgAdvice(healthStat.getTg())) // 중성지방 조언
                .hdlCholesterol(getHDLAdvice(healthStat.getHdlCholesterol())) // HDL콜레스테롤 조언 
                .fbg(getFbgAdvice(healthStat.getFbg())) // 공복혈당 조언
                .waistMeasurement(getWaistMeasurementAdvice(healthStat.getWaistMeasurement(), healthInfo.getGender())) // 복부비만 (허리둘레) 조언
                .build();
    }

    /*
    복부비만 (허리둘레)
    (좋음)
    남 = 90미만
    여 = 85미만
    (주의)
    남 = 90이상
    여 = 85이상
     */
    public static AdviceType getWaistMeasurementAdvice(float value, boolean gender) {
        if (gender){
            if (value < 90f){
                return AdviceType.정상;
            } else {
                return AdviceType.주의;
            }
        } else {
            if (value < 85f){
                return AdviceType.정상;
            } else {
                return AdviceType.주의;
            }
        }
    }

    /*
    공복혈당
    (좋음)
    100미만
    (주의)
    100~125
    (위험)
    126이상
     */
    public static AdviceType getFbgAdvice(float value) {
        if (value < 100f){
            return AdviceType.정상;
        } else if (value < 126f){
            return AdviceType.주의;
        } else {
            return AdviceType.위험;
        }
    }

    /*
    HDL콜레스테롤
    (좋음)
    60이상
    (주의)
    41~59
    (위험)
    40이하
     */
    public static AdviceType getHDLAdvice(float value) {
        if (value > 60f){
            return AdviceType.정상;
        } else if (value > 40f){
            return AdviceType.주의;
        } else {
            return AdviceType.위험;
        }
    }

    /*
    중성지방
    (좋음)
    150 미만
    (주의)
    150~199
    (위험)
    200이상
     */
    public static AdviceType getTgAdvice(float value) {
        if (value < 150f){
            return AdviceType.정상;
        } else if (value < 200f){
            return AdviceType.주의;
        } else {
            return AdviceType.위험;
        }
    }

    /*
    혈압
    (좋음)
    수축기혈압 120미만
    이완기혈압 80미만
    (주의)
    수축기혈압 120~139
    이완기혈압 80~89
    (위험)
    수축기혈압 140이상
    이완기혈압 90이상
     */
    public static AdviceType getBloodPressureAdvice(float low, float high) {
        if (low < 120f && high < 80f){
            return AdviceType.정상;
        } else if (low < 140f && high < 90f){
            return AdviceType.주의;
        } else {
            return AdviceType.위험;
        }
    }


    /*
    골격근
    (좋음)
    남 = 몸무게 * 0.4 이상
    여 = 체중 * 0.35 이상
    (주의)
    남 = 체중 * 0.4 미만
    여 = 체중 * 0.35 미만
     */
    public static AdviceType getSkeletalMuscleAdvice(float value, float weight, boolean gender) {
        if (gender) {
            if (weight * 0.4f <= value){
                return AdviceType.정상;
            } else {
                return AdviceType.위험;
            }
        } else {
            if (weight * 0.35f <= value){
                return AdviceType.정상;
            } else {
                return AdviceType.위험;
            }
        }
    }

    /*
    체지방
    (좋음)
    남 = 15 ~ 18
    여 = 20 ~ 25
    (주의)
    남 = 19 ~ 24
    여 = 26 ~ 29
    (위험)
    남 = 25 이상
    여 = 30 이상
     */
    public static AdviceType getFatPercentageAdvice(float value, boolean gender) {
        // 남
        if (gender){
            if (value < 15f){
                return AdviceType.위험;
            } else if (value <= 18f) {
                return AdviceType.정상;
            } else if (value <= 24f) {
                return AdviceType.주의;
            } else {
                return AdviceType.위험;
            }
        }
        // 여
        else {
            if (value < 20f) {
                return AdviceType.위험;
            } else if (value <= 25f) {
                return AdviceType.정상;
            } else if (value <= 29f) {
                return AdviceType.주의;
            } else {
                return AdviceType.위험;
            }
        }
    }

    /*
    건강조언 들어갈때 기준
    체중, bmi
    값 = 몸무게 / {키(m) * 키(m)}
    (위험)
    값<18.5 = 저체중
    (정상)
    18.5<= 값 < 23 = 정상
    (주의)
    23<= 값 < 25 = 과체중
    (위험)
    25 <= 값 <30 =비만
    30 <= 값 <35 =고도비만
    35 <= 값 = 초고도비만
     */
    public static AdviceType getBMIAdvice(float value) {
        if (value < 18.5f) {
            return AdviceType.위험;
        } else if (value < 23f) {
            return AdviceType.정상;
        } else if (value < 25f) {
            return AdviceType.주의;
        } else {
            return AdviceType.위험;
        }
    }

    public static String getWeightAdvice(float value) {
        if (value < 18.5f) {
            return "저체중";
        } else if (value < 23f) {
            return "정상";
        } else if (value < 25f) {
            return "과제중";
        } else if (value < 30f) {
            return "비만";
        } else if (value < 35f) {
            return "고도비만";
        } else {
            return "초고도비만";
        }
    }
//    public HealthAdviceDto getHealthAdvice(HealthAdviceGetReqDto healthAdviceGetReqDto) {
////        User user = userRepository.findById(getHealthAdviceReqDto.getUserNo()).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
//        StringDateTrans trans = new StringDateTrans(healthAdviceGetReqDto.getDate());
//        HealthAdvice healthAdviceDto = healthAdviceRepository.findByCreatetimeAndUserNo(trans.getStartDateTime(), healthAdviceGetReqDto.getUserNo()).orElseThrow();
//        return HealthAdviceDtoMapper.INSTANCE.entityToDto(healthAdviceDto);
//    }

//    @Override
//    public void deleteHealthAdvice(HealthAdviceDeleteReqDto healthAdviceDeleteReqDto) {
////        User user = userRepository.findById(deleteHealthAdviceReqDto.getUserNo()).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
//        healthAdviceRepository.deleteById(healthAdviceDeleteReqDto.getNo());
//    }

}
