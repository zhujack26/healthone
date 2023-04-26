package com.secui.healthone.domain.auth.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNo;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userNickname;

    @Column(nullable = false)
    private String userPlatformType;

    @Column(nullable = false)
    private String userEmail;

    private String userProfilePicture;

    @Column(nullable = false)
    private LocalDateTime userCreatetime;

    @Column(nullable = false)
    private boolean userActive;
}
