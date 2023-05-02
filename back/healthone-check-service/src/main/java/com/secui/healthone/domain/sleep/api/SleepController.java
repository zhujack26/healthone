package com.secui.healthone.domain.sleep.api;

import com.secui.healthone.domain.sleep.dto.SleepInsertDto;
import com.secui.healthone.domain.sleep.dto.SleepResDto;
import com.secui.healthone.domain.sleep.dto.SleepUpdateDto;
import com.secui.healthone.domain.sleep.entity.Sleep;
import com.secui.healthone.domain.sleep.service.SleepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sleep")
@Slf4j
@RequiredArgsConstructor
public class SleepController {
    private final SleepService sleepService;

    @GetMapping
    public ResponseEntity<List<SleepResDto>> getSleepData(@RequestParam String date) {
        List<SleepResDto> sleepList = sleepService.getSleepData(date);
        return ResponseEntity.ok().body(sleepList);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addSleepInfo(@RequestBody SleepInsertDto sleepInsertDto) {
        sleepService.addSleepInfo(sleepInsertDto);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateSleepInfo(@RequestBody SleepUpdateDto sleepUpdateDto) {
        sleepService.updateSleepInfo(sleepUpdateDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteSleepInfo(@RequestParam("no") Integer no) {
        sleepService.deleteSleepInfo(no);
    }
}
