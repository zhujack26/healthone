package com.secui.healthone.domain.walk.api;

import com.secui.healthone.domain.walk.entity.Walk;
import com.secui.healthone.domain.walk.service.WalkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private WalkService walkService;

    @GetMapping
    public ResponseEntity<List<Walk>> getWalkEntitiesForSevenDays(@RequestParam String dateTime) {
        List<Walk> walkList = walkService.getWalkEntitiesForSevenDays(dateTime);
        return ResponseEntity.ok().body(walkList);
    }

}
