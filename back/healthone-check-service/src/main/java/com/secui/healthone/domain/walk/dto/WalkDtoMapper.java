package com.secui.healthone.domain.walk.dto;

import com.secui.healthone.domain.walk.entity.Walk;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WalkDtoMapper {
    WalkDtoMapper INSTANCE = Mappers.getMapper(WalkDtoMapper.class);

    List<WalkResDto> entityToResDto(List<Walk> walk);

    WalkResDto entityToResDto(Walk walk);

    Walk reqDtoToEntity(WalkReqDto walk);

}
