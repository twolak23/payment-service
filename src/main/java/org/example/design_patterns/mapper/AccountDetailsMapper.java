package org.example.design_patterns.mapper;

import org.example.design_patterns.model.domain.entity.AccountEntity;
import org.example.design_patterns.model.rest.AccountDetailsResponse;

public class AccountDetailsMapper {

  public AccountDetailsResponse mapFromEntity(AccountEntity entity) {
    AccountDetailsResponse response = new AccountDetailsResponse(
            entity.getPerson().getFirstName(),
            entity.getPerson().getLastName(),
            entity.getBankProvider().getDescription(),
            entity.getIban(),
            entity.getBalance()
    );
    return response;
  }
}
