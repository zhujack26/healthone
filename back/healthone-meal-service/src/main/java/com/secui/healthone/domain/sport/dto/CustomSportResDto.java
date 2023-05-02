package com.secui.healthone.domain.sport.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class CustomSportResDto {
    private Integer no;
    private Integer userNo;
    private String name;
    private String consumeKcal;
}
