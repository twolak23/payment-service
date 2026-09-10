package org.example.payment_service.service.impl;

import org.example.payment_service.mapper.AccountDetailsMapper;
import org.example.payment_service.model.domain.entity.jpa.AccountEntity;
import org.example.payment_service.model.rest.AccountDetailsResponse;
import org.example.payment_service.repository.jpa.AccountRepository;
import org.example.payment_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("realAccount")
public class AccountServiceImpl implements AccountService {

  private final AccountRepository repository;
  private final AccountDetailsMapper mapper = new AccountDetailsMapper();
  @Autowired
  AccountServiceImpl(AccountRepository repository) {
    this.repository = repository;
  }

  @Override
  public AccountDetailsResponse getAccountDetails(UUID accountId, String pin) {
    AccountEntity entity = repository.getAccountEntityById(accountId);
    AccountDetailsResponse response = mapper.mapFromEntity(entity);
    return response;
  }

  @Override
  public AccountEntity getAccountByIban(String iban) {
    AccountEntity entity = repository.getAccountEntityByIban(iban);
    return entity;
  }

  @Override
  public AccountDetailsResponse getMaskedAccountDetails(UUID accountId) {
    AccountEntity entity = repository.getAccountEntityById(accountId);
    AccountDetailsResponse response = mapper.mapFromEntityAndMask(entity);
    return response;
  }

  @Override
  @Transactional(transactionManager = "transactionManager")
  public void transfer(AccountEntity source, AccountEntity target, double amount) {
    source.setBalance(source.getBalance() - amount);
    target.setBalance(target.getBalance() + amount);
    repository.saveAll(List.of(source, target));
  }

  public AccountRepository getRepository() {
    return repository;
  }
}
