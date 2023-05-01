package com.secui.healthone.domain.stress.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UploadUserStressInfoRequestDto {

    private int userNo;

    private int level;

    private LocalDateTime createTime;

}
