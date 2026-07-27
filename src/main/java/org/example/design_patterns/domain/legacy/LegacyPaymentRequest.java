package org.example.design_patterns.domain.legacy;

public class LegacyPaymentRequest {

  private String personFrom;
  private String personTo;
  private double amount;

  public LegacyPaymentRequest(String personFrom, String personTo, double amount) {
    this.personFrom = personFrom;
    this.personTo = personTo;
    this.amount = amount;
  }

  public String getPersonFrom() {
    return personFrom;
  }

  public void setPersonFrom(String personFrom) {
    this.personFrom = personFrom;
  }

  public String getPersonTo() {
    return personTo;
  }

  public void setPersonTo(String personTo) {
    this.personTo = personTo;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }
}
