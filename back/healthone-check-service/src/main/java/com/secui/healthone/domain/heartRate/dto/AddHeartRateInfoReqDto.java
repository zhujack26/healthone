package com.secui.healthone.domain.heartRate.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AddHeartRateInfoReqDto {
    private int userNo;
    private LocalDateTime createTime;
    private int count;
}
