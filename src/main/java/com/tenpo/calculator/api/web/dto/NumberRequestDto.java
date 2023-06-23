package com.tenpo.calculator.api.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Valid
public class NumberRequestDto {

    @Min(value = 1, message = "Number1 must be equal to or greater than 1")
    @NotNull(message = "The number1 attribute must not be null")
    private Double number1;

    @Min(value = 1, message = "Number2 must be equal to or greater than 1")
    @NotNull(message = "The number2 attribute must not be null")
    private Double number2;
}
