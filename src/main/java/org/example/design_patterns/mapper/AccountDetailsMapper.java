package org.example.design_patterns.mapper;

import org.example.design_patterns.model.domain.entity.jpa.AccountEntity;
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
  public AccountDetailsResponse mapFromEntityAndMask(AccountEntity entity) {
    entity.setIban(entity.getIban().substring(0,4).concat(" **** **** **** **** ****").concat(entity.getIban().substring(24)));

    entity.setCardNumber("**** **** **** ".concat(entity.getCardNumber().substring(12)));
    entity.getPerson().setLastName(entity.getPerson().getLastName().replaceAll("([a-z])",  "*"));
    AccountDetailsResponse response = new AccountDetailsResponse(
            entity.getPerson().getFirstName(),
            entity.getPerson().getLastName(),
            entity.getBankProvider().getDescription(),
            entity.getIban(),
            entity.getBalance()
    );
    return response;
  }
  /*
  {
    "iban": "PL61 **** **** **** 2874",
    "cardNumber": "**** **** **** 1111",
    "balance": null,
    "ownerFirstName": "J***",
    "ownerLastName": "***"
  }
  {
    "firstName": "John",
    "lastName": "Doe",
    "bankProvider": null,
    "iban": "*33719366", // should be PL64109024028851119733719366 --> PL64 **** **** **** **** 33719366
    "balance": 500.0
}
  */
}
