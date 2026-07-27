package org.example.design_patterns.domain;

import org.example.design_patterns.model.PaymentStatusEnum;

import java.util.UUID;

public class PaymentResponse {

  private UUID paymentId;
  private PaymentStatusEnum status;
  private String personFrom;
  private String personTo;

  public PaymentResponse() {}

  public UUID getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(UUID paymentId) {
    this.paymentId = paymentId;
  }

  public PaymentStatusEnum getStatus() {
    return status;
  }

  public void setStatus(PaymentStatusEnum status) {
    this.status = status;
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
}
