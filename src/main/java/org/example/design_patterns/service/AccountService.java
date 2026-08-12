package org.example.design_patterns.service;

import org.example.design_patterns.model.rest.AccountDetailsResponse;

import java.util.UUID;

public interface AccountService {
  public AccountDetailsResponse getAccountDetails(UUID accountId);
}
