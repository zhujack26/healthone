package com.secui.healthone.domain.sportrecord.dto;

import com.secui.healthone.domain.sportrecord.entity.SportRecord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SportRecordDtoMapper {

    SportRecordDtoMapper INSTANCE = Mappers.getMapper(SportRecordDtoMapper.class);

    SportRecordResDto entityToResDto(SportRecord sportRecord);

}
