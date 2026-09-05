package org.example.payment_service.service.proxy;

import org.example.payment_service.model.domain.entity.jpa.AccountEntity;
import org.example.payment_service.model.rest.AccountDetailsResponse;
import org.example.payment_service.repository.jpa.AccountRepository;
import org.example.payment_service.repository.reactive.AccountReactiveRepository;
import org.example.payment_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("accountProxy")
public class AccountServiceProxy implements AccountService {
  AccountServiceImpl serviceImpl;
  AccountRepository repository;

  @Autowired
  public AccountServiceProxy(AccountServiceImpl serviceImpl, AccountRepository repository) {
    this.serviceImpl = serviceImpl;
    this.repository = repository;
  }
  @Override
  public AccountDetailsResponse getAccountDetails(UUID accountId, String pin) {
    if(repository.existsByIdAndPin(accountId, pin)) {
      return serviceImpl.getAccountDetails(accountId, pin);
    }
    return serviceImpl.getMaskedAccountDetails(accountId);
  }

  @Override
  public AccountEntity getAccountByIban(String iban) {
    return null;
  }

  @Override
  public void transfer(AccountEntity source, AccountEntity target, double amount) {

  }
}
