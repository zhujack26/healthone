package com.secui.healthone.domain.data;

import com.opencsv.CSVWriter;
import com.secui.healthone.domain.meal.entity.Meal;
import com.secui.healthone.domain.meal.repository.MealRepository;
import com.secui.healthone.domain.sportrecord.entity.SportRecord;
import com.secui.healthone.domain.sportrecord.repository.SportRecordRepository;
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

    private final MealRepository mealRepository;
    private final SportRecordRepository sportRecordRepository;
    public HttpServletResponse writeMealDtoToCsv(HttpServletResponse response, Integer userNo) throws IOException {
        try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8))) {
            writer.writeNext(new String[]{"식별번호", "유저식별번호", "음식이름", "기록시간", "식사타입","섭취한 그램 수", "섭취한 칼로리"});
            List<Meal> mealList = mealRepository.findAllByUserNo(userNo);
            for (Meal entity : mealList) {
                writer.writeNext(new String[]{
                        String.valueOf(entity.getNo()),
                        String.valueOf(entity.getUserNo()),
                        entity.getName(),
                        entity.getCreateTime().toString(),
                        String.valueOf(entity.getMealType()),
                        String.valueOf(entity.getGram()),
                        String.valueOf(entity.getKcal())
                });
            }
            return response;
        }
    }

    public HttpServletResponse writeSportRecordDtoToCsv(HttpServletResponse response, Integer userNo) throws IOException {
        try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8))) {
            writer.writeNext(new String[]{"식별번호", "유저식별번호", "운동 이름", "기록시간", "운동시간","소비 칼로리", "심박수", "혈압"});
            List<SportRecord> sportRecordList = sportRecordRepository.findAllByUserNo(userNo);
            for (SportRecord entity : sportRecordList) {
                writer.writeNext(new String[]{
                        String.valueOf(entity.getNo()),
                        String.valueOf(entity.getUserNo()),
                        entity.getName(),
                        entity.getCreateTime().toString(),
                        String.valueOf(entity.getWorkTime()),
                        String.valueOf(entity.getConsumeCalorie()),
                        String.valueOf(entity.getHeartRate()),
                        String.valueOf(entity.getBloodPressure())
                });
            }
            return response;
        }
    }

}
