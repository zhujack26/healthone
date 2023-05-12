package com.secui.healthone.domain.auth.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    MEMBER("ROLE_USER", "NormalUser");

    private final String key;
    private final String title;
}