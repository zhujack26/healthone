package com.secui.healthone.domain.sportrecord.dto;

import com.secui.healthone.domain.sport.entity.CustomSport;
import com.secui.healthone.domain.sport.entity.Sport;
import com.secui.healthone.domain.sportrecord.entity.SportRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface SportRecordDtoMapper {

    SportRecordDtoMapper INSTANCE = Mappers.getMapper(SportRecordDtoMapper.class);

    @Mapping(source = "sportRecord.sport", target="sportResDto")
    @Mapping(source = "sportRecord.customSport", target="customSportResDto")
    SportRecordResDto entityToResDto(SportRecord sportRecord);

    @Mapping(source = "sportRecord.sport", target="sportResDto")
    @Mapping(source = "sportRecord.customSport", target="customSportResDto")
    List<SportRecordResDto> entityToResListDto(List<SportRecord> sportRecords);

    @Mapping(source = "sportRecordReqDto.no", target="no")
    @Mapping(source = "sportRecordReqDto.userNo", target="userNo")
    SportRecord reqDtoToEntity(SportRecordReqDto sportRecordReqDto, Sport sport, CustomSport customSport);

}
