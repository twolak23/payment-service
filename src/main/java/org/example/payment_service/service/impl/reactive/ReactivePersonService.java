package org.example.payment_service.service.impl.reactive;

import org.example.payment_service.model.domain.dto.PersonDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ReactivePersonService {
  Mono<PersonDTO> getPersonById(UUID id);
}
