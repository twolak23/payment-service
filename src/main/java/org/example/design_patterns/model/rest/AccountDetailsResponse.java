package org.example.design_patterns.model.rest;

public class AccountDetailsResponse {

  private String firstName;
  private String lastName;
  private String bankProvider;
  private String iban;

  public AccountDetailsResponse(String firstName, String lastName, String bankProvider, String iban) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.bankProvider = bankProvider;
    this.iban = iban;
  }

  public AccountDetailsResponse() {}

  public String getFirstName() {
    return firstName;
  }

  public AccountDetailsResponse setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public AccountDetailsResponse setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getBankProvider() {
    return bankProvider;
  }

  public AccountDetailsResponse setBankProvider(String bankProvider) {
    this.bankProvider = bankProvider;
    return this;
  }

  public String getIban() {
    return iban;
  }

  public AccountDetailsResponse setIban(String iban) {
    this.iban = iban;
    return this;
  }
}
