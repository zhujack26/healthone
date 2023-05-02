package com.secui.healthone.domain.sleep.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AddSleepInfoReqDto {

    private int userNo;

    private String sleepCreatetime;

    private String sleepStartSleepTime;

    private String sleepEndSleepTime;

}
