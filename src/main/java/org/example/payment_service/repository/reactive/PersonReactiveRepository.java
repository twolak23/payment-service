package org.example.payment_service.repository.reactive;

import org.example.payment_service.model.domain.entity.reactive.PersonEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface PersonReactiveRepository extends ReactiveCrudRepository<PersonEntity, UUID> { }