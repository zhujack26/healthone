package com.secui.healthone.domain.sport.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class CustomSportReqDto {
    private Integer no;
    private Integer userNo;
    private String name;
    private String consumeKcal;
}
