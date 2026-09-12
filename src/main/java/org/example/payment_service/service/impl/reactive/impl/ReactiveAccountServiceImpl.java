package org.example.payment_service.service.impl.reactive.impl;

import org.example.payment_service.mapper.ReactiveAccountDetailsMapper;
import org.example.payment_service.model.domain.entity.reactive.AccountEntity;
import org.example.payment_service.model.rest.AccountDetailsResponse;
import org.example.payment_service.repository.reactive.AccountReactiveRepository;
import org.example.payment_service.service.impl.reactive.ReactiveAccountService;
import org.example.payment_service.service.impl.reactive.ReactivePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service("realAccountReactive")
public class ReactiveAccountServiceImpl implements ReactiveAccountService {

  private final AccountReactiveRepository repository;
  private final ReactivePersonService personService;
  private final ReactiveAccountDetailsMapper mapper = new ReactiveAccountDetailsMapper();
  @Autowired
  ReactiveAccountServiceImpl(AccountReactiveRepository repository, ReactivePersonService personService) {
    this.repository = repository;
    this.personService = personService;
  }

  @Override
  public Mono<AccountDetailsResponse> getAccountDetails(UUID accountId, String pin) {
    return repository.getAccountEntityById(accountId)
            .switchIfEmpty(Mono.error(new Exception()))
            .flatMap((accountEntity ->
                    personService.getPersonById(accountEntity.getPersonId())
                        .switchIfEmpty(Mono.error(new Exception()))
                        .map(personDTO -> mapper.mapFromEntity(accountEntity, personDTO))
            ));
  }

  @Override
  public Mono<AccountEntity> getAccountByIban(String iban) {
    return repository.getAccountEntityByIban(iban)
            .switchIfEmpty(Mono.error(new Exception()))
            .map(account -> account);
  }

  @Override
  public Mono<UUID> getPersonIdByAccountId(UUID id) {
    return repository.getAccountEntityById(id)
        .switchIfEmpty(Mono.error(new Exception()))
        .map(AccountEntity::getPersonId);
  }

  @Override
  public Mono<AccountDetailsResponse> getMaskedAccountDetails(UUID accountId) {
    return repository.getAccountEntityById(accountId)
            .switchIfEmpty(Mono.error(new Exception()))
        .flatMap((accountEntity ->
            personService.getPersonById(accountEntity.getPersonId())
                .switchIfEmpty(Mono.error(new Exception()))
                .map(personDTO -> mapper.mapFromEntityAndMask(accountEntity, personDTO))
        ));
  }

  @Override
  @Transactional(transactionManager = "connectionFactoryTransactionManager")
  public Mono<Void> transfer(AccountEntity source, AccountEntity target, double amount) {
    source.setBalance(source.getBalance() - amount);
    target.setBalance(target.getBalance() + amount);
    return repository.saveAll(List.of(source, target)).then(Mono.empty());
  }

  public AccountReactiveRepository getRepository() {
    return repository;
  }
}
