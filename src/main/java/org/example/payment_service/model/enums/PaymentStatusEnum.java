package org.example.payment_service.model.enums;

public enum PaymentStatusEnum {
  ACCEPTED("Accepted"),
  IN_PROGRESS("Accepted"),
  REJECTED("Rejected");

  private final String statusName;

  PaymentStatusEnum(String statusName) {
    this.statusName = statusName;
  }

  public String getStatusName() {
    return statusName;
  }
}

