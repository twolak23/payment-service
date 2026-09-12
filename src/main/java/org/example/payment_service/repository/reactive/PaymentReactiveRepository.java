package org.example.payment_service.repository.reactive;

import org.example.payment_service.model.domain.entity.reactive.PaymentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentReactiveRepository extends ReactiveCrudRepository<PaymentEntity, UUID> {
}
