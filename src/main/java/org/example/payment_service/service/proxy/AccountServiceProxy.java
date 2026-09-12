package org.example.payment_service.service.proxy;

import org.example.payment_service.model.domain.entity.jpa.AccountEntity;
import org.example.payment_service.model.rest.AccountDetailsResponse;
import org.example.payment_service.repository.jpa.AccountRepository;
import org.example.payment_service.repository.reactive.AccountReactiveRepository;
import org.example.payment_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("accountProxy")
public class AccountServiceProxy implements AccountService {
  AccountService realAccountService;
  AccountRepository repository;
  AccountReactiveRepository reactiveRepository;

  @Autowired
  public AccountServiceProxy(@Qualifier("realAccount") AccountService realAccountService,
                             AccountRepository repository,
                             AccountReactiveRepository reactiveRepository) {
    this.realAccountService = realAccountService;
    this.repository = repository;
    this.reactiveRepository = reactiveRepository;
  }
  @Override
  public AccountDetailsResponse getAccountDetails(UUID accountId, String pin) {
    if(repository.existsByIdAndPin(accountId, pin)) {
      return realAccountService.getAccountDetails(accountId, pin);
    }
    return realAccountService.getMaskedAccountDetails(accountId);
  }

  @Override
  public AccountEntity getAccountByIban(String iban) {
    return null;
  }

  @Override
  public void transfer(AccountEntity source, AccountEntity target, double amount) {

  }

  @Override
  public AccountDetailsResponse getMaskedAccountDetails(UUID accountId) {
    return null;
  }
}
