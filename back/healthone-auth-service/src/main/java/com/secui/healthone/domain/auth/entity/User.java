package com.secui.healthone.domain.auth.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @Column(name="user_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_nickname",nullable = true)
    private String name;

    @Column(name="user_email",nullable = false)
    private String email;
    @Column(name="user_profile_picture")
    private String picture;
    @Column(name="user_active")
    private Boolean active;


    @Column(name= "user_createtime")
    private LocalDateTime createtime;

    @Enumerated(EnumType.STRING)
    @Column(name= "user_role")
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

}