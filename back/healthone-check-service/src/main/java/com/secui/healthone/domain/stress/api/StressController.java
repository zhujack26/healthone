package com.secui.healthone.domain.stress.api;

import com.secui.healthone.domain.stress.dto.UploadUserStressInfoRequestDto;
import com.secui.healthone.domain.stress.service.StressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stress")
@Slf4j
public class StressController {

    @Autowired
    private StressService stressService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void uploadUserStressInfo(@RequestBody UploadUserStressInfoRequestDto stressInfoDto) {
        stressService.uploadUserStressInfo(stressInfoDto);
    }

}
