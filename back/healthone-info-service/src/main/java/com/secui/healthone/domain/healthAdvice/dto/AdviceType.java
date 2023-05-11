package com.secui.healthone.domain.healthAdvice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdviceType {
    NORMAL("NORMAL" , "건강조언: 정상"),
    WARN("WARN", "건강조언: 주의"),
    DANGER("DANGER", "건강조언: 위험");

    private final String name;
    private final String description;
}
