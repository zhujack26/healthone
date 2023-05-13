package com.secui.healthone.domain.data;

import com.opencsv.CSVWriter;
import com.secui.healthone.domain.healthInfo.entity.HealthInfo;
import com.secui.healthone.domain.healthInfo.repository.HealthInfoRepository;
import com.secui.healthone.domain.healthStat.repository.HealthStatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvService {

    private final HealthInfoRepository healthInfoRepository;
    private final HealthStatRepository healthStatRepository;


    public HttpServletResponse writeHealthInfoDtoToCsv(HttpServletResponse response, Integer userNo) throws IOException {
        try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8))) {
            writer.writeNext(new String[]
                    {"유저 헬스 정보 식별번호", "유저식별번호", "유저 닉네임", "유저 가입일시", "유저 성별", "유저 생년월일",
                        "유저 키", "유저 체중", "활동량" , "목표 걸음 수", "목표 취침시간", "목표 기상시간", "목표 수면시간"});
            List<HealthInfo> healthInfoList = healthInfoRepository.findAllByUserNo(userNo);
            for (HealthInfo entity : healthInfoList) {
                writer.writeNext(new String[]{
                        String.valueOf(entity.getNo()),
                        String.valueOf(entity.getUserNo()),
                        entity.getNickname(),
                        entity.getCreateTime().toString(),
                        entity.getGender().toString(),
                        entity.getBirthdate().toString(),
                        String.valueOf(entity.getHeight()),
                        String.valueOf(entity.getWeight()),
                        String.valueOf(entity.getWorkRate()),
                        String.valueOf(entity.getStepGoal()),
                        String.valueOf(entity.getSleepTime()),
                        String.valueOf(entity.getWakeUpTime()),
                        String.valueOf(entity.getSleepGoal())
                });
            }
            return response;
        }
    }

//    public HttpServletResponse writeSleepDtoToCsv(HttpServletResponse response, Integer userNo) throws IOException {
//        try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8))) {
//            writer.writeNext(new String[]{"식별번호", "유저식별번호", "기록시간", "취침시간", "기상시간"});
//            List<Sleep> sleepList = sleepRepository.findAllByUserNo(userNo);
//            for (Sleep entity : sleepList) {
//                writer.writeNext(new String[]{
//                        String.valueOf(entity.getNo()),
//                        String.valueOf(entity.getUserNo()),
//                        entity.getCreateTime().toString(),
//                        String.valueOf(entity.getStartSleepTime()),
//                        String.valueOf(entity.getEndSleepTime())
//                });
//            }
//            return response;
//        }
//    }
}
