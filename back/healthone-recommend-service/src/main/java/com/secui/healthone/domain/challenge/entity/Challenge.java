package com.secui.healthone.domain.challenge.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "challenge")
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_no") // 챌린지 데이터 식별번호
    private Integer no;
    @Column(name = "challenge_name") // 챌린지 이름
    private String name;
    @Column(name = "challenge_introduce") // 챌린지 소개
    private String introduce;
    @Column(name = "challenge_total_work_count") // 챌린지 총 운동 횟수
    private String totalWorkCount;
    @Column(name = "challenge_total_period") // 챌린지 총 기간
    private String totalPeriod;
    @Column(name = "challenge_level") // 챌린지 난이도
    @Enumerated(EnumType.STRING)
    private ChallengeLevel level;
    @Column(name = "challenge_avg_work_time") // 평균 운동 시간
    private Integer avgWorkTime;
    @Column(name = "challenge_sport_equipment_check") // 필요 운동기구 여부
    private boolean sportEquipmentCheck;
    @Column(name = "challenge_sport_equipment") // 필요 운동 기구
    private String equipment;
    @Column(name = "challenge_program_type") // 프로그램 유형
    private String programType;
    @Column(name = "challenge_recommend_week") // 주간 추천 운동 요일
    private String recommendWeek;
    @Column(name = "challenge_youtube_link") // 유튜브 영상 링크
    private String youtubeLink;
    @Column(name = "challenge_thumbnail_link") // 썸네일 이미지 링크
    private String thumbnailLink;
    @Column(name = "challenge_participants") // 챌린지 참가자 수
    private Integer participants;
    @Column(name = "challenge_hits") // 챌린지 조회수
    private Integer hits;
}
