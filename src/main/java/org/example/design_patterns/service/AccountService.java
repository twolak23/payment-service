package org.example.design_patterns.service;

import org.example.design_patterns.model.domain.entity.jpa.AccountEntity;
import org.example.design_patterns.model.rest.AccountDetailsResponse;

import java.util.UUID;

public interface AccountService {
  AccountDetailsResponse getAccountDetails(UUID accountId, String pin);
  AccountEntity getAccountByIban(String iban);
  void transfer(AccountEntity source, AccountEntity target, double amount);
}
