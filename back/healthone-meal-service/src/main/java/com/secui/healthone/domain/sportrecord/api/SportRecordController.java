package com.secui.healthone.domain.sportrecord.api;

import com.secui.healthone.domain.sportrecord.dto.SportRecordResDto;
import com.secui.healthone.domain.sportrecord.service.SportRecordService;
import com.secui.healthone.global.response.RestApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sportrecord")
@RequiredArgsConstructor
@Tag(name = "SportRecord", description = "운동 기록 관련 컨트롤러")
public class SportRecordController {
    private final SportRecordService sportRecordService;

    @GetMapping
    public RestApiResponse<List<SportRecordResDto>> getSportRecordList(@RequestParam("date") String date) {
        Integer userNo = 1;
        return new RestApiResponse<>(date +"날짜 운동 기록 정보 리스트 조회 성공", sportRecordService.getSportRecordList(date, userNo));
    }
}
