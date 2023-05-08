package com.secui.healthone.domain.userSetting.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_setting_no")
    private int no;
    @Column(name = "user_setting_user_no")
    private int userNo;
    @Column(name = "user_setting_allow_location")
    private boolean allowLocation;
    @Column(name = "user_setting_accessories")
    private String accessories;
    @Column(name = "user_setting_notification_method")
    private String notificationMethod;
}
