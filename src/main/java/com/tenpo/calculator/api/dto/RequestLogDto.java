package com.tenpo.calculator.api.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RequestLogDto {

   private Long id;

   private LocalDateTime time;

   private String host;

   private String path;

   private String requestBody;

   private Integer httpResponseCode;

   private String responseBody;

   private Integer responseTimeMs;

   private String responseErrorCode;

   private String responseErrorDescription;

}
