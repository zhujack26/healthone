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
    @Column(name = "user_no")
    private int no;
    @Column(name = "user_id")
    private String id;
    @Column(name = "user_nickname")
    private String nickname;
    @Column(name = "user_platform_type")
    private String platformType;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_profile_picture")
    private String profilePicture;
    @Column(name = "user_createtime")
    private LocalDateTime createtime;
    @Column(name = "user_active")
    private boolean active;
}
