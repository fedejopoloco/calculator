package com.tenpo.calculator.api.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ErrorResponseDto {

    private Integer status;

    private String error;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> fields;

    public ErrorResponseDto(Integer status, String error, String message, List<String> fields) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.fields = fields;
    }

    public ErrorResponseDto(Integer status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public ErrorResponseDto() {
    }
}
