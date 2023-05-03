package com.secui.healthone.domain.heartRate.api;

import com.secui.healthone.domain.heartRate.dto.AddHeartRateInfoReqDto;
import com.secui.healthone.domain.heartRate.dto.HeartRateResDto;
import com.secui.healthone.domain.heartRate.service.HeartRateService;
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
    public ResponseEntity<List<HeartRateResDto>> getWeeklyHeartRate(@RequestParam String dateTime) {
        List<HeartRateResDto> heartRateList = heartRateService.getWeeklyHeartRate(dateTime);
        return ResponseEntity.ok().body(heartRateList);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addHeartRateInfo(@RequestBody AddHeartRateInfoReqDto addHeartRateInfoReqDto) {
        heartRateService.addHeartRateInfo(addHeartRateInfoReqDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteHeartRateInfo(@RequestParam String no) {
        heartRateService.deleteHeartRateInfo(no);
    }


}
