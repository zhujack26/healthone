package com.secui.healthone.domain.sleep.api;

import com.secui.healthone.domain.sleep.dto.AddSleepInfoReqDto;
import com.secui.healthone.domain.sleep.dto.UpdateSleepInfoReqDto;
import com.secui.healthone.domain.sleep.entity.Sleep;
import com.secui.healthone.domain.sleep.service.SleepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class SleepController {

    @Autowired
    private SleepService sleepService;

    @GetMapping
    public ResponseEntity<List<Sleep>> getSleepData(@RequestParam String date) {
        List<Sleep> sleepList = sleepService.getSleepData(date);
        return ResponseEntity.ok().body(sleepList);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addSleepInfo(@RequestBody AddSleepInfoReqDto addSleepInfoReqDto) {
        sleepService.addSleepInfo(addSleepInfoReqDto);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateSleepInfo(@RequestBody UpdateSleepInfoReqDto updateSleepInfoReqDto) {
        sleepService.updateSleepInfo(updateSleepInfoReqDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteSleepInfo(@RequestParam String no) {
        sleepService.deleteSleepInfo(no);
    }
}
