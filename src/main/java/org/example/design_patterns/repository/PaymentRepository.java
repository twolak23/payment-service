package org.example.design_patterns.repository;

import org.example.design_patterns.domain.legacy.LegacyPaymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepository extends CrudRepository<LegacyPaymentEntity, UUID> {
}
