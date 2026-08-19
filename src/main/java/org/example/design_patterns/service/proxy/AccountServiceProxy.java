package org.example.design_patterns.service.proxy;

import org.example.design_patterns.model.domain.entity.AccountEntity;
import org.example.design_patterns.model.rest.AccountDetailsResponse;
import org.example.design_patterns.repository.AccountRepository;
import org.example.design_patterns.service.AccountService;
import org.example.design_patterns.service.impl.AccountServiceImpl;
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
