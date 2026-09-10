package org.example.payment_service.service.impl.reactive;

import org.example.payment_service.model.domain.entity.reactive.AccountEntity;
import org.example.payment_service.model.rest.AccountDetailsResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ReactiveAccountService {
  Mono<AccountDetailsResponse> getAccountDetails(UUID accountId, String pin);
  Mono<AccountEntity> getAccountByIban(String iban);
  Mono<Void> transfer(AccountEntity source, AccountEntity target, double amount);
  Mono<AccountDetailsResponse> getMaskedAccountDetails(UUID accountId);
}
