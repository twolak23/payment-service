package org.example.payment_service.service;

import org.example.payment_service.model.domain.entity.jpa.AccountEntity;
import org.example.payment_service.model.rest.AccountDetailsResponse;

import java.util.UUID;

public interface AccountService {
  AccountDetailsResponse getAccountDetails(UUID accountId, String pin);
  AccountEntity getAccountByIban(String iban);
  void transfer(AccountEntity source, AccountEntity target, double amount);
  public AccountDetailsResponse getMaskedAccountDetails(UUID accountId);
}
