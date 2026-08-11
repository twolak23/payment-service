package org.example.design_patterns.model.domain.entity;

import jakarta.persistence.*;
import org.example.design_patterns.model.enums.BankProviderEnum;

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

  public PersonEntity getPerson() {
    return person;
  }

  public void setPerson(PersonEntity person) {
    this.person = person;
  }
}
