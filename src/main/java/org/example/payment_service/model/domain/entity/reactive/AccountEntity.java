package org.example.payment_service.model.domain.entity.reactive;

import org.springframework.data.relational.core.mapping.*;
import org.example.payment_service.model.enums.BankProviderEnum;
import org.springframework.data.annotation.Id;
import java.util.UUID;

@org.springframework.data.relational.core.mapping.Table(name = "account_reactive")
public class AccountEntity {

  @Id
  @Column
  private UUID id;

  @Column("iban")
  private String iban;

  @Column("bank_provider")
  private BankProviderEnum bankProvider;

  @Column("balance")
  private double balance;

  @Column("card_number")
  private String cardNumber;

  @Column("pin")
  private String pin;

  @Column("person_id")
  private UUID personId;

  public UUID getId() {
    return id;
  }

  public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public BankProviderEnum getBankProvider() {
    return bankProvider;
  }

  public void setBankProvider(BankProviderEnum bankProvider) {
    this.bankProvider = bankProvider;
  }

  public double getBalance() {
    return balance;
  }

  public AccountEntity setBalance(double balance) {
    this.balance = balance;
    return this;
  }

  public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public UUID getPersonId() {
    return personId;
  }

  public void setPerson(UUID personId) {
    this.personId = personId;
  }
}
