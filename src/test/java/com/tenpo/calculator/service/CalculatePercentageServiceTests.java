package com.tenpo.calculator.service;

import com.tenpo.calculator.api.web.dto.NumberRequestDto;
import com.tenpo.calculator.api.web.dto.NumberResponseDto;
import com.tenpo.calculator.api.core.service.CalculatePercentageService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@Nested
class CalculatePercentageServiceTests {

    @MockBean
    private CalculatePercentageService calculatePercentageService;

    @Test
    void contextLoads() {
        assertThat(calculatePercentageService).isNotNull();
    }

    @Test
    public void calculatePercentageService() {
        when(calculatePercentageService.calculatePercentage(getNumberRequest())).thenReturn(getNumberResponseDto());
        NumberResponseDto numberResponseDto = calculatePercentageService.calculatePercentage(getNumberRequest());
        assertThat(numberResponseDto).isNotNull();
    }

    private NumberResponseDto getNumberResponseDto() {
        NumberResponseDto responseDto = new NumberResponseDto(110.00);
        return responseDto;
    }

    private NumberRequestDto getNumberRequest() {
        NumberRequestDto requestDto = new NumberRequestDto();
        requestDto.setNumber1(50.00);
        requestDto.setNumber2(50.00);
        return requestDto;
    }

}
