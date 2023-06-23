package com.tenpo.calculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.calculator.api.web.controller.CalculatorController;
import com.tenpo.calculator.api.web.dto.NumberRequestDto;
import com.tenpo.calculator.api.web.dto.NumberResponseDto;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Nested
class CalculatorControllerTests {

    private static final String URL_POST_CALCULATE_PERCENTAGE = "/calculate/percentage";

    @MockBean
    private CalculatorController calculatorController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        assertThat(calculatorController).isNotNull();
    }

    @Test
    public void calculatePercentage() throws Exception {
        when(calculatorController.calculatePercentage(getNumberRequest())).thenReturn(getNumberResponseDto());

        ObjectMapper mapeador = new ObjectMapper();
        String jsonRequest = mapeador.writeValueAsString(getNumberRequest());

        mockMvc.perform(post(URL_POST_CALCULATE_PERCENTAGE).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void calculatePercentageBadRequest() throws Exception {
        when(calculatorController.calculatePercentage(null)).thenReturn(getNumberResponseDto());

        mockMvc.perform(post(URL_POST_CALCULATE_PERCENTAGE).contentType(MediaType.APPLICATION_JSON).content(""))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    private ResponseEntity<NumberResponseDto> getNumberResponseDto() {
        NumberResponseDto responseDto = new NumberResponseDto(110.00);
        return ResponseEntity.ok(responseDto);
    }

    private NumberRequestDto getNumberRequest() {
        NumberRequestDto requestDto = new NumberRequestDto();
        requestDto.setNumber1(50.00);
        requestDto.setNumber2(50.00);
        return requestDto;
    }

}
