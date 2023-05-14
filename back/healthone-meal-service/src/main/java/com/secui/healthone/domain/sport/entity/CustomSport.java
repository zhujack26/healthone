package com.secui.healthone.domain.sport.entity;

import com.secui.healthone.domain.sport.dto.CustomSportReqDto;
import com.secui.healthone.domain.sport.dto.CustomSportResDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customsport")
public class CustomSport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customsport_no") // 사용자 운동 데이터 식별번호
    private Integer no;
    @Column(name = "user_no") // 회원 식별번호
    private Integer userNo;
    @Column(name = "customsport_name", nullable = false) // 운동 이름
    private String name;
    @Column(name = "customsport_consumekcal", nullable = false) // 시간당 소모 칼로리
    private Integer consumeKcal;

    public void update(CustomSportReqDto customSportReqDto){
        this.name = customSportReqDto.getName();
        this.consumeKcal = customSportReqDto.getConsumeKcal();
    }
}
