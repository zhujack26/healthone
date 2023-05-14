package com.secui.healthone.domain.data;

import com.opencsv.CSVWriter;
import com.secui.healthone.domain.healthInfo.entity.HealthInfo;
import com.secui.healthone.domain.healthInfo.repository.HealthInfoRepository;
import com.secui.healthone.domain.healthStat.entity.HealthStat;
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
                        "유저 키", "유저 체중", "활동량" , "목표 걸음 수", "목표 취침시간", "목표 기상시간"});
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
//                        String.valueOf(entity.getSleepGoal())
                });
            }
            return response;
        }
    }

    public HttpServletResponse writeHealthStatDtoToCsv(HttpServletResponse response, Integer userNo) throws IOException {
        try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8))) {
            writer.writeNext(new String[]
                    {"헬스 기록 식별번호", "유저식별번호", "기록시간", "키", "체중", "BMI", "체지방률", "골격근량", "최저혈압" , "최고혈압", "중성지방", "공복혈당", "HDL 콜레스테롤"});
            List<HealthStat> healthStatList = healthStatRepository.findAllByUserNo(userNo);
            for (HealthStat entity : healthStatList) {
                writer.writeNext(new String[]{
                        String.valueOf(entity.getNo()),
                        String.valueOf(entity.getUserNo()),
                        entity.getCreateTime().toString(),
                        String.valueOf(entity.getHeight()),
                        String.valueOf(entity.getWeight()),
                        String.valueOf(entity.getBmi()),
                        String.valueOf(entity.getBodyFatPercentage()),
                        String.valueOf(entity.getWaistMeasurement()),
                        String.valueOf(entity.getLowBloodPressure()),
                        String.valueOf(entity.getHighBloodPressure()),
                        String.valueOf(entity.getTg()),
                        String.valueOf(entity.getFbg()),
                        String.valueOf(entity.getHdlCholesterol())
                });
            }
            return response;
        }
    }
}
