package com.tenpo.calculator.api.controller;

import com.tenpo.calculator.api.dto.RequestLogDto;
import com.tenpo.calculator.api.service.RequestLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/request")
@Api(tags = "Calculate percentage", value = "Calculate percentage")
public class RequestLogController {

    @Autowired
    private RequestLogService requestLogService;

    @GetMapping(path = "/logs", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Percentage of the sum between 2 numbers")
    public ResponseEntity<Page<RequestLogDto>> getRequestLogs(Pageable pageable) {
        return ResponseEntity.ok(requestLogService.getAllRequestLog(pageable));
    }
}
