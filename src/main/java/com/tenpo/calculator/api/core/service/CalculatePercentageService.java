package com.tenpo.calculator.api.core.service;

import com.tenpo.calculator.api.infra.client.beeceptor.BeeceptorFeingClient;
import com.tenpo.calculator.api.web.dto.NumberRequestDto;
import com.tenpo.calculator.api.web.dto.NumberResponseDto;
import com.tenpo.calculator.api.web.dto.PercentageResponsetDto;
import com.tenpo.calculator.api.infra.entity.PercentageEntity;
import com.tenpo.calculator.api.infra.repository.PercentageRepository;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class CalculatePercentageService {

	@Autowired
	private BeeceptorFeingClient beeceptorFeingClient;

	@Autowired
	private PercentageRepository percentageRepository;

	@Retryable(value = Exception.class, maxAttempts = 3)
	public NumberResponseDto calculatePercentage(NumberRequestDto requestDto) {
		return addNumberAndPercentage(requestDto.getNumber1(), requestDto.getNumber2(),
				findPercentage());
	}

	public NumberResponseDto  addNumberAndPercentage(Double number1, Double number2, Double percentage) {
		Double amount = number1 + number2;
		Double result = amount + (amount/100)*percentage;
		return new NumberResponseDto(result);
	}

	public Double findPercentage() {
		AtomicReference<Double> result = new AtomicReference<>();
		try {
		ResponseEntity<PercentageResponsetDto> response = beeceptorFeingClient.findPercentage();
		if (response.getStatusCode().is2xxSuccessful() && Objects.nonNull(response.getBody())) {
			PercentageResponsetDto percentageResponsetDto = response.getBody();
			PercentageEntity percentageEntity = new PercentageEntity();
			result.set(percentageResponsetDto.getPercentage().getValue());
			percentageEntity.setValue(result.get());
			percentageEntity.setCreationDate(LocalDateTime.now());
			percentageRepository.save(percentageEntity);
			return result.get();
		}
		} catch (FeignException ex) {
			percentageRepository.findFirstByOrderByIdDesc().ifPresent(percentageEntity ->
					result.set(percentageEntity.getValue()));
			if (Objects.isNull(result.get())) {
				log.error("There was an error getting the percentage", ex.getMessage());
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"There was an error getting the percentage");
			}
			return result.get();
		}
		return null;
	}

}