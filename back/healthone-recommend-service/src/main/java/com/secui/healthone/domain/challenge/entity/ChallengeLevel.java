package com.secui.healthone.domain.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ChallengeLevel {

    LOW("LOW","쉬움"),
    NORMAL("NORMAL","보통"),
    HIGH("HIGH","어려움");

    private final String level;
    private final String displayName;

    public static ChallengeLevel of(String level) {
        return Arrays.stream(ChallengeLevel.values())
                .filter(r -> r.getLevel().equals(level))
                .findAny()
                .orElse(NORMAL);
    }
}
