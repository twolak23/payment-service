package org.example.design_patterns.model.rest;

public class PaymentRequest {

  private String sourceAccount;
  private String targetAccount;
  private double amount;

  public PaymentRequest(String sourceAccount, String targetAccount, double amount) {
    this.sourceAccount = sourceAccount;
    this.targetAccount = targetAccount;
    this.amount = amount;
  }

  public String getSourceAccount() {
    return sourceAccount;
  }

  public void setSourceAccount(String sourceAccount) {
    this.sourceAccount = sourceAccount;
  }

  public String getTargetAccount() {
    return targetAccount;
  }

  public void setTargetAccount(String targetAccount) {
    this.targetAccount = targetAccount;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }
}
