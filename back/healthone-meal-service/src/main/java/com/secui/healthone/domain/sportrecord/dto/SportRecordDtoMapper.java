package com.secui.healthone.domain.sportrecord.dto;

import com.secui.healthone.domain.sportrecord.entity.SportRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SportRecordDtoMapper {

    SportRecordDtoMapper INSTANCE = Mappers.getMapper(SportRecordDtoMapper.class);

    @Mapping(source = "sportRecord.sport", target="sportResDto")
    @Mapping(source = "sportRecord.customSport", target="customSportResDto")
    SportRecordResDto entityToResDto(SportRecord sportRecord);

    @Mapping(source = "sportRecord.sport", target="sportResDto")
    @Mapping(source = "sportRecord.customSport", target="customSportResDto")
    List<SportRecordResDto> entityToResListDto(List<SportRecord> sportRecords);
}
