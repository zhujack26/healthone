package com.secui.healthone.domain.walk.api;

import com.secui.healthone.domain.walk.dto.WalkResDto;
import com.secui.healthone.domain.walk.service.WalkService;
import com.secui.healthone.global.response.RestApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/walk")
@Slf4j
@RequiredArgsConstructor
public class WalkController {

    private final WalkService walkService;

    @GetMapping
    public RestApiResponse<List<WalkResDto>> getWalkEntitiesForSevenDays(@RequestParam String dateTime) {
        return new RestApiResponse<>("걸음 수 리스트 조회 성공" , walkService.getWalkEntitiesForSevenDays(dateTime));
    }

    @GetMapping("/detail")
    public RestApiResponse<List<WalkResDto>> getDetailedWalkInfo(@RequestParam String date) {
        return new RestApiResponse<>("걸음 수 세부 정보 조회 성공" , walkService.getDetailedWalkInfo(date));
    }

}
