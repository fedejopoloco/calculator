package com.tenpo.calculator.api.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.Valid;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Valid
public class PercentageResponseValueDto {


    private Double value;

}
