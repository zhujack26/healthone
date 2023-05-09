package com.secui.healthone.domain.participants.dto;

import com.secui.healthone.domain.challenge.entity.Challenge;
import com.secui.healthone.domain.participants.entity.Participants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "챌린지 참가 등록, 취소 DTO")
public class ParticipantsCommonDto {
    private Integer no;
    private Integer userNo; // 유저 식별번호
    private Integer challengeNo; // 챌린지 번호

    public ParticipantsCommonDto(Participants entity){
        this.no = entity.getNo();
        this.userNo = entity.getUserNo();
        this.challengeNo = entity.getChallenge().getNo();
    }

    public Participants toEntity(){
        return Participants.builder()
                .no(no)
                .userNo(userNo)
                .challenge(Challenge.builder().no(challengeNo).build())
                .build();
    }
}
