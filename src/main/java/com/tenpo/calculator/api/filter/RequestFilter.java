package com.tenpo.calculator.api.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tenpo.calculator.api.infra.entity.RequestLogEntity;
import com.tenpo.calculator.api.handler.ErrorResponseDto;
import com.tenpo.calculator.api.core.service.RequestLogService;
import com.tenpo.calculator.api.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 3)
public class RequestFilter extends OncePerRequestFilter {

   @Autowired
   private ObjectMapper objectMapper;

   @Autowired
   private RequestLogService requestLogService;

   @Override
   protected boolean shouldNotFilter(HttpServletRequest request) {
      return request.getServletPath().matches("^/(swagger-ui|swagger-resources|v2/api-docs|request).*$");
   }


   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
         throws ServletException, IOException {

      // Cache
      CacheRequestWrapper cachedRequest = new CacheRequestWrapper(request);
      ContentCachingResponseWrapper cachedResponse = new ContentCachingResponseWrapper(response);

      // Process request
      StopWatch watch = new StopWatch();
      watch.start();
      filterChain.doFilter(cachedRequest, cachedResponse);
      watch.stop();

      // Save request
      if (!StringUtils.equalsAnyIgnoreCase(HttpMethod.OPTIONS.name(), cachedRequest.getMethod())) {

         Integer httpResponseCode = cachedResponse.getStatus();
         String requestPayload = cachedRequest.getBody();

         String responseBody = LogUtil.processMessage(StreamUtils.copyToString(cachedResponse.getContentInputStream(),
                 StandardCharsets.UTF_8));

         // Log data
         RequestLogEntity requestLog = new RequestLogEntity();
         requestLog.setHost(request.getHeader(Constants.HOST));
         requestLog.setPath(request.getServletPath());
         requestLog.setTime(LocalDateTime.now());
         requestLog.setRequestBody(LogUtil.processMessage(requestPayload));
         requestLog.setHttpResponseCode(httpResponseCode);
         requestLog.setResponseBody(LogUtil.processMessage(responseBody));
         requestLog.setResponseTimeMs((int) watch.getTotalTimeMillis());

         setErrorCodeAndDescription(responseBody, requestLog);

         requestLogService.saveRequestLog(requestLog);
      }

      // Copy response
      cachedResponse.copyBodyToResponse();

   }

   private void setErrorCodeAndDescription(String responseBody, RequestLogEntity requestLog) {

      if (requestLog.getHttpResponseCode() != 200) {
         try {
            // Cast to error response dto
            ErrorResponseDto errorResponseDto = objectMapper.readValue(responseBody, ErrorResponseDto.class);
            requestLog.setResponseErrorCode(errorResponseDto.getError());
            requestLog.setResponseErrorDescription(errorResponseDto.getMessage());
         } catch (JsonProcessingException | IllegalArgumentException ex) {
            log.error("Error casting response to ErrorResponseDto", ex.getMessage());
         }
      }
   }

}
