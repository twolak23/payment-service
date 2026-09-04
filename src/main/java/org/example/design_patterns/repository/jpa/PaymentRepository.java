package org.example.design_patterns.repository.jpa;

import org.example.design_patterns.model.domain.entity.jpa.PaymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity, UUID> {
}
