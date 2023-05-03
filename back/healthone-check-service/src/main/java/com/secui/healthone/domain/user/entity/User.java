package com.secui.healthone.domain.user.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
