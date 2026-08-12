package org.example.design_patterns.service.impl;

import org.example.design_patterns.mapper.AccountDetailsMapper;
import org.example.design_patterns.model.domain.entity.AccountEntity;
import org.example.design_patterns.model.rest.AccountDetailsResponse;
import org.example.design_patterns.repository.AccountRepository;
import org.example.design_patterns.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

  private final AccountRepository repository;
  private final AccountDetailsMapper mapper = new AccountDetailsMapper();
  @Autowired
  AccountServiceImpl(AccountRepository repository) {
    this.repository = repository;
  }

  @Override
  public AccountDetailsResponse getAccountDetails(UUID accountId) {
    AccountEntity entity = repository.getAccountEntityById(accountId);
    return mapper.mapFromEntity(entity);
  }

  @Override
  public AccountEntity getAccountByIban(String iban) {
    AccountEntity entity = repository.getAccountEntityByIban(iban);
    return entity;
  }

  @Override
  @Transactional
  public void transfer(AccountEntity source, AccountEntity target, double amount) {
    source.setBalance(source.getBalance() - amount);
    target.setBalance(target.getBalance() + amount);
    repository.saveAll(List.of(source, target));
  }

  public AccountRepository getRepository() {
    return repository;
  }
}
