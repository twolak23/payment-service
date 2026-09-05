package org.example.payment_service.model.domain.entity.jpa;

import jakarta.persistence.*;
import org.example.payment_service.model.enums.BankProviderEnum;
import org.example.payment_service.utils.EncryptionConverter;

import java.util.UUID;

@Entity(name = "account")
public class AccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column
  private UUID id;

  @Column(name = "iban", nullable = false, unique = true)
  private String iban;

  @Column(name = "bank_provider")
  @Enumerated(EnumType.STRING)
  private BankProviderEnum bankProvider;

  @Column(name = "balance")
  private double balance;

  @Column(name = "card_number", nullable = false)
  private String cardNumber;

  @Column(name = "pin", nullable = false)
  @Convert(converter = EncryptionConverter.class)
  private String pin;

  @ManyToOne
  @JoinColumn(name = "person_id", referencedColumnName = "id")
  private PersonEntity person;

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

  public void setBalance(double balance) {
    this.balance = balance;
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

  public PersonEntity getPerson() {
    return person;
  }

  public void setPerson(PersonEntity person) {
    this.person = person;
  }
}
