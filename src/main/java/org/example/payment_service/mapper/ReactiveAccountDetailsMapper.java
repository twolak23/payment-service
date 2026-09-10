package org.example.payment_service.mapper;

import org.example.payment_service.model.domain.entity.reactive.AccountEntity;
import org.example.payment_service.model.domain.entity.reactive.PersonEntity;
import org.example.payment_service.model.rest.AccountDetailsResponse;

public class ReactiveAccountDetailsMapper {

  public AccountDetailsResponse mapFromEntity(AccountEntity account, PersonEntity person) {
    AccountDetailsResponse response = new AccountDetailsResponse(
            person.getFirstName(),
            person.getLastName(),
            account.getBankProvider().getDescription(),
            account.getIban(),
            account.getBalance()
    );
    return response;
  }
  public AccountDetailsResponse mapFromEntityAndMask(AccountEntity account, PersonEntity person) {
    account.setIban(account.getIban().substring(0,4).concat(" **** **** **** **** ****").concat(account.getIban().substring(24)));

    account.setCardNumber("**** **** **** ".concat(account.getCardNumber().substring(12)));
    person.setLastName(person.getLastName().replaceAll("([a-z])",  "*"));
    AccountDetailsResponse response = new AccountDetailsResponse(
            person.getFirstName(),
            person.getLastName(),
            account.getBankProvider().getDescription(),
            account.getIban(),
            account.getBalance()
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
