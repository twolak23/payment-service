package org.example.payment_service.mapper;

import org.example.payment_service.model.domain.dto.PersonDTO;
import org.example.payment_service.model.domain.entity.reactive.AccountEntity;
import org.example.payment_service.model.rest.AccountDetailsResponse;

public class ReactiveAccountDetailsMapper {

  public AccountDetailsResponse mapFromEntity(AccountEntity account, PersonDTO person) {
    return new AccountDetailsResponse(
            person.firstName(),
            person.lastName(),
            account.getBankProvider().getDescription(),
            account.getIban(),
            account.getBalance()
    );
  }
  public AccountDetailsResponse mapFromEntityAndMask(AccountEntity account, PersonDTO person) {
    account.setIban(account.getIban().substring(0,4).concat(" **** **** **** **** ****").concat(account.getIban().substring(24)));

    account.setCardNumber("**** **** **** ".concat(account.getCardNumber().substring(12)));
    ;
    return new AccountDetailsResponse(
        person.firstName().replaceAll("([a-z])",  "*"),
        person.lastName().replaceAll("([a-z])",  "*"),
        account.getBankProvider().getDescription(),
        account.getIban(),
        account.getBalance()
    );
  }
}
