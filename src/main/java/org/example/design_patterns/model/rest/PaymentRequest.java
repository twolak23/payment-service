package org.example.design_patterns.model.rest;

public class PaymentRequest {

  private String sourceAccountIban;
  private String targetAccountIban;
  private double amount;

  public PaymentRequest(String sourceAccountIban, String targetAccountIban, double amount) {
    this.sourceAccountIban = sourceAccountIban;
    this.targetAccountIban = targetAccountIban;
    this.amount = amount;
  }

  public String getSourceAccountIban() {
    return sourceAccountIban;
  }

  public void setSourceAccountIban(String sourceAccountIban) {
    this.sourceAccountIban = sourceAccountIban;
  }

  public String getTargetAccountIban() {
    return targetAccountIban;
  }

  public void setTargetAccountIban(String targetAccountIban) {
    this.targetAccountIban = targetAccountIban;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }
}
