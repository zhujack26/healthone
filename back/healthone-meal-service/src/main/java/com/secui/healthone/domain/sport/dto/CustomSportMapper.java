package com.secui.healthone.domain.sport.dto;

import com.secui.healthone.domain.sport.entity.CustomSport;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomSportMapper {

    CustomSportMapper INSTANCE = Mappers.getMapper(CustomSportMapper.class);

    CustomSportResDto customSportToCustomSportResDto(CustomSport customSport);
    CustomSportResDto customSportReqDtoToCustomSportResDto(CustomSportReqDto customSportReqDto);
    List<CustomSportResDto> customSportToCustomSportResListDto(List<CustomSport> customSport);
    CustomSport customSportReqDtoToCustomSport(CustomSportReqDto customSportResDto);
}
