package com.tenpo.calculator.api.core.service;

import com.tenpo.calculator.api.core.mapper.RequestLogMapper;
import com.tenpo.calculator.api.web.dto.RequestLogDto;
import com.tenpo.calculator.api.infra.entity.RequestLogEntity;
import com.tenpo.calculator.api.infra.repository.RequestLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@Slf4j
public class RequestLogService {

	@Autowired
	private RequestLogMapper requestLogMapper;

	@Autowired
	private RequestLogRepository requestLogRepository;

	@Async
	public void saveRequestLog(RequestLogEntity requestLog) {
		requestLogRepository.save(requestLog);
	}

	public Page<RequestLogDto> getAllRequestLog(Pageable pageable) {
		Page<RequestLogEntity> requestLogList = requestLogRepository.findAll(pageable);
		if (requestLogList.hasContent()) {
			return requestLogList.map(requestLogMapper::toDto);
		} else {
			log.info("Request logs not found.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request logs not found.");
		}
	}

}