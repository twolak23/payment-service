package org.example.payment_service.repository.reactive;

import org.example.payment_service.model.domain.entity.reactive.AccountEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface AccountReactiveRepository extends ReactiveCrudRepository<AccountEntity, UUID> {
  Mono<AccountEntity> getAccountEntityById(UUID id);

  Mono<AccountEntity> getAccountEntityByIban(String iban);

  Mono<Boolean> existsByIdAndPin(UUID id, String pin);
}
