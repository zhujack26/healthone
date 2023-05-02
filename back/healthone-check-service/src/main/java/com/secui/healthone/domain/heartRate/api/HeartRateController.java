package com.secui.healthone.domain.heartRate.api;

import com.secui.healthone.domain.heartRate.dto.AddHeartRateInfoReqDto;
import com.secui.healthone.domain.heartRate.entity.HeartRate;
import com.secui.healthone.domain.heartRate.service.HeartRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/heart-rate")
@Slf4j
@RequiredArgsConstructor
public class HeartRateController {

    @Autowired
    private HeartRateService heartRateService;

    @GetMapping
    public ResponseEntity<List<HeartRate>> getWeeklyHeartRate(@RequestParam String dateTime) {
        List<HeartRate> heartRateList = heartRateService.getWeeklyHeartRate(dateTime);
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
