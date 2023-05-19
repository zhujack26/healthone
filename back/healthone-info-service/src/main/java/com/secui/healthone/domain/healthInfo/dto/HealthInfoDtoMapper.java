package com.secui.healthone.domain.healthInfo.dto;

import com.secui.healthone.domain.healthInfo.entity.HealthInfo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        uses = {
                HealthInfo.class,
                HealthInfoDto.class
        }
)
public interface HealthInfoDtoMapper {

    HealthInfoDtoMapper INSTANCE = Mappers.getMapper(HealthInfoDtoMapper.class);

    List<HealthInfoDto> entityListToDtoList(List<HealthInfo> healthInfoList);

    HealthInfoDto entityToDto(HealthInfo healthInfo);

    HealthInfo dtoToEntity(HealthInfoDto healthInfoDto);

}
