package com.tenpo.calculator.api.infra.client.beeceptor;

import com.tenpo.calculator.api.web.dto.PercentageResponsetDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "BeeceptorFeingClient", url = "${app.client.beeceptor.url}", path = "${app.client.beeceptor.path}")
public interface BeeceptorFeingClient {

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Cacheable(cacheNames = "percentage")
	public ResponseEntity<PercentageResponsetDto> findPercentage();

}
