package com.secui.healthone.domain.heartRate.api;

import com.secui.healthone.domain.heartRate.dto.AddHeartRateInfoReqDto;
import com.secui.healthone.domain.heartRate.dto.HeartRateResDto;
import com.secui.healthone.domain.heartRate.service.HeartRateService;
import com.secui.healthone.domain.walk.dto.WalkResDto;
import com.secui.healthone.global.response.RestApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        List<HeartRateResDto> heartRateList = heartRateService.getWeeklyHeartRate(dateTime);
        return new RestApiResponse<>("심박수 7일치 정보 조회 성공", heartRateList);
    }

    @PostMapping
    public RestApiResponse<?> addHeartRateInfo(@RequestBody AddHeartRateInfoReqDto addHeartRateInfoReqDto) {
        heartRateService.addHeartRateInfo(addHeartRateInfoReqDto);
        return new RestApiResponse<>("심박수 등록 성공", null);
    }

    @DeleteMapping
    public RestApiResponse<?> deleteHeartRateInfo(@RequestParam String no) {
        heartRateService.deleteHeartRateInfo(no);
        return new RestApiResponse<>("심박수 삭제 성공", null);
    }


}
