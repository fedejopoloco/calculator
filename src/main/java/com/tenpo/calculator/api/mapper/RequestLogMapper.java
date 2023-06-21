package com.tenpo.calculator.api.mapper;

import com.tenpo.calculator.api.dto.RequestLogDto;
import com.tenpo.calculator.api.entity.RequestLogEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RequestLogMapper {

   RequestLogDto toDto(RequestLogEntity entity);

   RequestLogEntity toEntity(RequestLogDto dto);

}
