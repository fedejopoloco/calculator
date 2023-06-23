package com.tenpo.calculator.api.infra.repository;

import com.tenpo.calculator.api.infra.entity.RequestLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestLogRepository extends JpaRepository<RequestLogEntity, Integer> {

}
