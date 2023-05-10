package com.secui.healthone.domain.challenge.dto;

import com.secui.healthone.domain.challenge.entity.Challenge;
import com.secui.healthone.domain.challenge.entity.ChallengeLevel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@Schema(description = "챌린지 응답 DTO")
public class ChallengeResDto {
    @Schema(description = "챌린지 식별번호")
    private Integer no;
    @Schema(description = "챌린지 이름")
    private String name;
    @Schema(description = "챌린지 소개")
    private String introduce;
    @Schema(description = "총 운동횟수")
    private String totalWorkCount;
    @Schema(description = "총 기간(주)")
    private String totalPeriod;
    @Schema(description = "챌린지 난이도")
    private ChallengeLevel level;
    @Schema(description = "평균 운동시간(분)")
    private Integer avgWorkTime;
    @Schema(description = "필요 운동기구 여부")
    private boolean sportEquipmentCheck;
    @Schema(description = "운동 기구 정보")
    private String equipment;
    @Schema(description = "프로그램 유형")
    private String programType;
    @Schema(description = "주간 추천 운동 요일")
    private String recommendWeek;
    @Schema(description = "유튜브 운동 영상 링크")
    private String youtubeLink;
    @Schema(description = "썸네일 링크")
    private String thumbnailLink;
    @Schema(description = "챌린지 참여자수")
    private Integer participants;
    @Schema(description = "챌린지 조회수")
    private Integer hits;
    @Schema(description = "회원 챌린지 참가 여부")
    private boolean participantsCheck;

    @Builder
    public ChallengeResDto(Integer no, String name, String introduce, String totalWorkCount, String totalPeriod, ChallengeLevel level, Integer avgWorkTime,
                           boolean sportEquipmentCheck, String equipment, String programType, String recommendWeek, String youtubeLink,
                           String thumbnailLink, Integer participants, Integer hits, boolean participantsCheck) {
        this.no = no;
        this.name = name;
        this.introduce = introduce;
        this.totalWorkCount = totalWorkCount;
        this.totalPeriod = totalPeriod;
        this.level = level;
        this.avgWorkTime = avgWorkTime;
        this.sportEquipmentCheck = sportEquipmentCheck;
        this.equipment = equipment;
        this.programType = programType;
        this.recommendWeek = recommendWeek;
        this.youtubeLink = youtubeLink;
        this.thumbnailLink = thumbnailLink;
        this.participants = participants;
        this.hits = hits;
        this.participantsCheck = participantsCheck;
    }

    @Builder
    public ChallengeResDto(Challenge entity){
        this.no = entity.getNo();
        this.name = entity.getName();
        this.introduce = entity.getIntroduce();
        this.totalWorkCount = entity.getTotalWorkCount();
        this.totalPeriod = entity.getTotalPeriod();
        this.level = entity.getLevel();
        this.avgWorkTime = entity.getAvgWorkTime();
        this.sportEquipmentCheck = entity.isSportEquipmentCheck();
        this.equipment = entity.getEquipment();
        this.programType = entity.getProgramType();
        this.recommendWeek = entity.getRecommendWeek();
        this.youtubeLink = entity.getYoutubeLink();
        this.thumbnailLink = entity.getThumbnailLink();
        this.participants = entity.getParticipants();
        this.hits = entity.getHits();
    }

    public ChallengeResDto getDetailInfoDto(Challenge entity, boolean participantsCheck){
        return ChallengeResDto.builder()
                .no(entity.getNo())
                .name(entity.getName())
                .introduce(entity.getIntroduce())
                .totalWorkCount(entity.getTotalWorkCount())
                .totalPeriod(entity.getTotalPeriod())
                .level(entity.getLevel())
                .avgWorkTime(entity.getAvgWorkTime())
                .sportEquipmentCheck(entity.isSportEquipmentCheck())
                .equipment(entity.getEquipment())
                .programType(entity.getProgramType())
                .recommendWeek(entity.getRecommendWeek())
                .youtubeLink(entity.getYoutubeLink())
                .thumbnailLink(entity.getThumbnailLink())
                .participants(entity.getParticipants())
                .hits(entity.getHits())
                .participantsCheck(participantsCheck)
                .build();
    }
}
