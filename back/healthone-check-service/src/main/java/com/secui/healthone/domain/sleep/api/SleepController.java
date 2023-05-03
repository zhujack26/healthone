package com.secui.healthone.domain.sleep.api;

import com.secui.healthone.domain.sleep.dto.SleepInsertDto;
import com.secui.healthone.domain.sleep.dto.SleepResDto;
import com.secui.healthone.domain.sleep.dto.SleepUpdateDto;
import com.secui.healthone.domain.sleep.entity.Sleep;
import com.secui.healthone.domain.sleep.service.SleepService;
import com.secui.healthone.domain.walk.dto.WalkResDto;
import com.secui.healthone.global.response.RestApiResponse;
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
    public RestApiResponse<List<SleepResDto>> getSleepData(@RequestParam String date) {
        List<SleepResDto> sleepList = sleepService.getSleepData(date);
        return new RestApiResponse<>("수면 세부 정보 조회 성공" , sleepList);
    }

    @PostMapping
    public RestApiResponse<?> addSleepInfo(@RequestBody SleepInsertDto sleepInsertDto) {
        sleepService.addSleepInfo(sleepInsertDto);
        return new RestApiResponse<>("수면 정보 등록 성공" , null);
    }

    @PatchMapping
    public RestApiResponse<?> updateSleepInfo(@RequestBody SleepUpdateDto sleepUpdateDto) {
        sleepService.updateSleepInfo(sleepUpdateDto);
        return new RestApiResponse<>("수면 정보 수정 성공" , null);
    }

    @DeleteMapping
    public RestApiResponse<?> deleteSleepInfo(@RequestParam("no") Integer no) {
        sleepService.deleteSleepInfo(no);
        return new RestApiResponse<>("수면 정보 삭제 성공" , null);
    }
}
