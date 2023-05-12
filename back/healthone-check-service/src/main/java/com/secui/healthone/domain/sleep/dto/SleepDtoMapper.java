package com.secui.healthone.domain.sleep.dto;

import com.secui.healthone.domain.sleep.entity.Sleep;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SleepDtoMapper {
    SleepDtoMapper INSTANCE = Mappers.getMapper(SleepDtoMapper.class);

    List<SleepResDto> entityToResDto(List<Sleep> sleep);
    SleepResDto entityToResDto(Sleep sleep);
    Sleep insertDtoToEntity(SleepInsertDto sleepInsertDto);
    Sleep updateDtoToEntity(SleepUpdateDto sleepUpdateDto);

}
