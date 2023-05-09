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
@Schema(description = "챌린지 응답 DTO")
public class ChallengeResDto {
    private Integer no;
    private String name;
    private String introduce;
    private String totalWorkCount;
    private String totalPeriod;
    private ChallengeLevel level;
    private Integer avgWorkTime;
    private boolean sportEquipmentCheck;
    private String equipment;
    private String programType;
    private String recommendWeek;
    private String youtubeLink;
    private String thumbnailLink;
    private Integer participants;
    private Integer hits;

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
}
