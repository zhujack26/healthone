package com.secui.healthone.domain.heartRate.dto;

import com.secui.healthone.domain.heartRate.entity.HeartRate;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Slice;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        uses = {
                HeartRate.class,
                HeartRateResDto.class
        }
)
public interface HeartRateDtoMapper {
    HeartRateDtoMapper INSTANCE = Mappers.getMapper(HeartRateDtoMapper.class);

    List<HeartRateResDto> entityToResDto(List<HeartRate> heartRate);
    HeartRateResDto entityToResDto(HeartRate heartRate);
    HeartRate insertDtoToEntity(HeartRateInsertDto heartRateInsertDto);
    HeartRateResDto insertDtoToResDto(HeartRateInsertDto heartRateInsertDto);
}
