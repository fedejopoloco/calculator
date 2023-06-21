package com.tenpo.calculator.api.service;

import com.tenpo.calculator.api.dto.RequestLogDto;
import com.tenpo.calculator.api.entity.RequestLogEntity;
import com.tenpo.calculator.api.mapper.RequestLogMapper;
import com.tenpo.calculator.api.repository.RequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.logging.Logger;

@Service
public class RequestLogService {

	private static Logger logger = Logger.getLogger(RequestLogService.class.getName());

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
		if (!requestLogList.isEmpty()) {
			return requestLogList.map(requestLogMapper::toDto);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request logs not found.");
		}
	}

}