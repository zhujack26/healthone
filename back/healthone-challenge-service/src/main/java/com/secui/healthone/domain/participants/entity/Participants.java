package com.secui.healthone.domain.participants.entity;

import com.secui.healthone.domain.challenge.entity.Challenge;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "participants")
public class Participants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participants_no") //참가 식별번호
    private Integer no;
    @Column(name = "user_no")
    private Integer userNo; // 유저 식별번호
    @Column(name = "challenge_no")
    private Integer challengeNo; // 챌린지
}
