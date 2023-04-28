package com.secui.healthone.domain.sport.api;


import com.secui.healthone.domain.sport.dto.SportResDto;
import com.secui.healthone.domain.sport.service.SportService;
import com.secui.healthone.global.response.RestApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sport")
@RequiredArgsConstructor
public class SportController {

    private final SportService sportService;

    // 운동 데이터 검색
    @GetMapping("/search")
    public RestApiResponse<List<SportResDto>> searchSport (@RequestParam("name") String name) {
        return new RestApiResponse<>("운동 데이터 검색 성공", sportService.searchSport(name));
    }

    // 운동 데이터 단건 조회
    @GetMapping
    public RestApiResponse<SportResDto> getSportData (@RequestParam("no") Integer no) {
        return new RestApiResponse<>("운동 데이터 조회 성공", sportService.getSport(no));
    }
}
