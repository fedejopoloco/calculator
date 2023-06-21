package com.tenpo.calculator.api.controller;


import com.tenpo.calculator.api.dto.NumberRequestDto;
import com.tenpo.calculator.api.dto.NumberResponseDto;
import com.tenpo.calculator.api.service.CalculatePercentageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/calculate")
@Api(tags = "Calculate percentage", value = "Calculate percentage")
public class CalculatorController {

    @Autowired
    private CalculatePercentageService calculatePercentageService;

    @PostMapping(path = "/percentage" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Percentage of the sum between 2 numbers")
    public ResponseEntity<NumberResponseDto> calculatePercentage(@Valid @RequestBody NumberRequestDto requestDto) {
        return ResponseEntity.ok(calculatePercentageService.calculatePercentage(requestDto));
    }
}
