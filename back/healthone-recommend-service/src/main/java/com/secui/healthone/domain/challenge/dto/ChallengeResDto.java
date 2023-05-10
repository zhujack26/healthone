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
