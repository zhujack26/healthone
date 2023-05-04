package com.secui.healthone.domain.walk.dto;

import com.secui.healthone.domain.walk.entity.Walk;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface WalkDtoMapper {
    WalkDtoMapper INSTANCE = Mappers.getMapper(WalkDtoMapper.class);

    List<WalkResDto> entityToResDto(List<Walk> walk);

    WalkResDto entityToResDto(Walk walk);

//    @Mapping(target = "walkReqDto.createtime", expression = "java(LocalDateTime.now())")
    Walk reqDtoToEntity(WalkReqDto walkReqDto);

    WalkResDto resDtoToReqDto(WalkReqDto walkReqDto);

}
