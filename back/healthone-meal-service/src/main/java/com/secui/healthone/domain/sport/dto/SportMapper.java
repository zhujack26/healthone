package com.secui.healthone.domain.sport.dto;

import com.secui.healthone.domain.sport.entity.Sport;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SportMapper {
    SportMapper INSTANCE = Mappers.getMapper(SportMapper.class);

    SportResDto sportToSportResDto(Sport sport);

    List<SportResDto> sportToSportResListDto(List<Sport> sport);

}
