package com.secui.healthone.domain.healthAdvice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdviceType {
    정상("정상" , "건강조언: 정상"),
    주의("주의", "건강조언: 주의"),
    위험("위험", "건강조언: 위험");

    private final String name;
    private final String description;
}
