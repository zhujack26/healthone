//package com.secui.healthone.domain.healthAdvice.dto;
//
//import com.secui.healthone.domain.healthAdvice.entity.HealthAdvice;
//import org.mapstruct.Mapper;
//import org.mapstruct.NullValueMappingStrategy;
//import org.mapstruct.factory.Mappers;
//
//import java.util.List;
//
//@Mapper(
//        componentModel = "spring",
//        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
//        uses = {
//                HealthAdvice.class,
//                HealthAdviceDto.class
//        }
//)
//public interface HealthAdviceDtoMapper {
//    HealthAdviceDtoMapper INSTANCE = Mappers.getMapper(HealthAdviceDtoMapper.class);
//
//    List<HealthAdviceDto> entityListToDtoList(List<HealthAdvice> healthAdviceList);
//
//    HealthAdviceDto entityToDto(HealthAdvice healthAdvice);
//
//    HealthAdvice dtoToEntity(HealthAdviceDto healthAdviceDto);
//}
