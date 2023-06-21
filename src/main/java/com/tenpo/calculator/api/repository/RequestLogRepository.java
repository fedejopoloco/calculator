package com.tenpo.calculator.api.repository;

import com.tenpo.calculator.api.entity.RequestLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestLogRepository extends JpaRepository<RequestLogEntity, Integer> {

}
