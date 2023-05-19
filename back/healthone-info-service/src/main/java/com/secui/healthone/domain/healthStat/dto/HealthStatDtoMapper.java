package com.secui.healthone.domain.healthStat.dto;

import com.secui.healthone.domain.healthStat.entity.HealthStat;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        uses = {
                HealthStat.class,
                HealthStatDto.class
        }
)
public interface HealthStatDtoMapper {
    HealthStatDtoMapper INSTANCE = Mappers.getMapper(HealthStatDtoMapper.class);

    List<HealthStatDto> entityListToDtoList(List<HealthStat> healthStatList);
    HealthStatDto entityToDto(HealthStat healthStat);
    HealthStat dtoToEntity(HealthStatDto healthStatDto);
}
