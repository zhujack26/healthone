package com.secui.healthone.domain.heartRate.dto;

import com.secui.healthone.domain.heartRate.entity.HeartRate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HeartRateDtoMapper {
    HeartRateDtoMapper INSTANCE = Mappers.getMapper(HeartRateDtoMapper.class);

    List<HeartRateResDto> entityToResDto(List<HeartRate> heartRate);

    HeartRateResDto entityToResDto(HeartRate heartRate);

    HeartRate reqDtoToEntity(HeartRateReqDto heartRate);

}
