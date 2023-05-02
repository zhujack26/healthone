package com.secui.healthone.domain.sleep.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSleepInfoReqDto {

    private String no;

    private String sleepCreatetime;

    private String sleepStartSleepTime;

    private String sleepEndSleepTime;

}
