package com.secui.healthone.domain.heartRate.api;

import com.secui.healthone.domain.heartRate.dto.AddHeartRateInfoReqDto;
import com.secui.healthone.domain.heartRate.dto.HeartRateResDto;
import com.secui.healthone.domain.heartRate.service.HeartRateService;
import com.secui.healthone.global.response.RestApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/heart-rate")
@Slf4j
@RequiredArgsConstructor
public class HeartRateController {
    private final HeartRateService heartRateService;

    @GetMapping
    public RestApiResponse<List<HeartRateResDto>> getWeeklyHeartRate(@RequestParam String dateTime) {
        return new RestApiResponse<>(dateTime + "날짜 심박수 리스트 조회 성공", heartRateService.getWeeklyHeartRate(dateTime));
    }

    @PostMapping
    public RestApiResponse<HeartRateResDto> addHeartRateInfo(@RequestBody AddHeartRateInfoReqDto addHeartRateInfoReqDto) {
        return new RestApiResponse<>("심박수 추가 성공", heartRateService.addHeartRateInfo(addHeartRateInfoReqDto));
    }

    @DeleteMapping
    public RestApiResponse<Void> deleteHeartRateInfo(@RequestParam Integer no) {
        heartRateService.deleteHeartRateInfo(no);
        return new RestApiResponse<>("심박수 데이터 삭제 성공", null);
    }
}
