package com.secui.healthone.domain.stress.api;

import com.secui.healthone.domain.stress.dto.UploadUserStressInfoRequestDto;
import com.secui.healthone.domain.stress.entity.Stress;
import com.secui.healthone.domain.stress.service.StressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stress")
@Slf4j
public class StressController {

    @Autowired
    private StressService stressService;

    @GetMapping
    public ResponseEntity<List<Stress>> getStressList(@RequestParam String date) {
        return ResponseEntity.ok().body(stressService.getStressList(date, 0));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void uploadUserStressInfo(@RequestBody UploadUserStressInfoRequestDto stressInfoDto) {
        stressService.uploadUserStressInfo(stressInfoDto);
    }

}
