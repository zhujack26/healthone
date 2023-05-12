package com.secui.healthone.domain.challenge.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@Schema(description = "챌린지 메인 페이지 응답 DTO")
public class ChallengeMainResDto {
    @Schema(description = "인기있는 챌린지 리스트")
    private List<ChallengeResDto> popularChallengeList;
    @Schema(description = "많이 찾는 챌린지 리스트 (조회수 기준)")
    private List<ChallengeResDto> frequentlyChallengeList;
    @Schema(description = "전체 챌린지 참여자 수")
    private Integer totalChallengeParticipantsCount;
    @Schema(description = "회원 챌린지 참여자 수")
    private Integer userChallengeParticipantsCount;
}
