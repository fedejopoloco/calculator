package com.tenpo.calculator.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Valid
public class NumberRequestDto {

    @NotNull(message = "The number1 attribute must not be null")
    private Double number1;

    @NotNull(message = "The number2 attribute must not be null")
    private Double number2;
}
