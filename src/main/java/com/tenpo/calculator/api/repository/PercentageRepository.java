package com.tenpo.calculator.api.repository;

import com.tenpo.calculator.api.entity.PercentageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PercentageRepository extends JpaRepository<PercentageEntity, Integer> {

    Optional<PercentageEntity> findFirstByOrderByIdDesc();

}
