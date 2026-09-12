package org.example.payment_service.service.proxy;

import org.example.payment_service.model.domain.entity.reactive.AccountEntity;
import org.example.payment_service.model.rest.AccountDetailsResponse;
import org.example.payment_service.repository.reactive.AccountReactiveRepository;
import org.example.payment_service.service.impl.reactive.ReactiveAccountService;
import org.example.payment_service.utils.EncryptionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service("accountProxyReactive")
public class ReactiveAccountServiceProxy implements ReactiveAccountService {
  ReactiveAccountService realAccountService;
  AccountReactiveRepository repository;
  EncryptionConverter converter;

  @Autowired
  public ReactiveAccountServiceProxy(@Qualifier("realAccountReactive") ReactiveAccountService realAccountService,
                                     AccountReactiveRepository repository,
                                     EncryptionConverter converter) {
    this.realAccountService = realAccountService;
    this.repository = repository;
    this.converter = converter;
  }
  @Override
  public Mono<AccountDetailsResponse> getAccountDetails(UUID accountId, String pin) {
    return repository.existsByIdAndPin(accountId, converter.convertToDatabaseColumn(pin))
        .flatMap(exists -> {
          if(exists) {
            return realAccountService.getAccountDetails(accountId, pin);
          }
          return realAccountService.getMaskedAccountDetails(accountId);
        });
  }

  @Override
  public Mono<AccountEntity> getAccountByIban(String iban) {
    return null;
  }

  @Override
  public Mono<Void> transfer(AccountEntity source, AccountEntity target, double amount) {
    return null;
  }

  @Override
  public Mono<AccountDetailsResponse> getMaskedAccountDetails(UUID accountId) {
    return null;
  }

  @Override
  public Mono<UUID> getPersonIdByAccountId(UUID id) {
    return null;
  }
}
