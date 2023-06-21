package com.tenpo.calculator.api.entity;

import java.time.LocalDateTime;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "request_log")
public class RequestLogEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   private LocalDateTime time;

   private String host;

   private String path;

   @Column(name = "request_body")
   private String requestBody;

   @Column(name = "http_response_code")
   private Integer httpResponseCode;

   @Column(name = "response_body")
   private String responseBody;

   @Column(name = "response_time_ms")
   private Integer responseTimeMs;

   @Column(name = "response_error_code")
   private String responseErrorCode;

   @Column(name = "response_error_description")
   private String responseErrorDescription;

}
