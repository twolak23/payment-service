package org.example.payment_service.model.domain.entity.reactive;

import org.example.payment_service.model.enums.PaymentStatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.*;
import java.util.Date;
import java.util.UUID;


@Table("payment")
public class PaymentEntity {

  @Id
  @Column
  private UUID id;

  @Column(value = "source_account")
  private UUID sourceAccountId;

  @Column("target_account")
  private UUID targetAccountId;

  @Column("amount")
  private double amount;

  @Column("payment_date")
  private Date paymentDate;

  @Column("status")
  private PaymentStatusEnum status;

  public PaymentEntity() {
  }

  public PaymentEntity(UUID sourceAccountId, UUID targetAccountId, double amount, Date paymentDate, PaymentStatusEnum status) {
    this.sourceAccountId = sourceAccountId;
    this.targetAccountId = targetAccountId;
    this.amount = amount;
    this.paymentDate = paymentDate;
    this.status = status;
  }

  public PaymentEntity(UUID sourceAccountId, UUID targetAccountId, double amount, Date paymentDate) {
    this.sourceAccountId = sourceAccountId;
    this.targetAccountId = targetAccountId;
    this.amount = amount;
    this.paymentDate = paymentDate;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public PaymentStatusEnum getStatus() {
    return status;
  }

  public PaymentEntity setStatus(PaymentStatusEnum status) {
    this.status = status;
    return this;
  }

  public Date getPaymentDate() {
    return paymentDate;
  }

  public PaymentEntity setPaymentDate(Date paymentDate) {
    this.paymentDate = paymentDate;
    return this;
  }

  public UUID getTargetAccountId() {
    return targetAccountId;
  }

  public PaymentEntity setTargetAccountId(UUID targetAccountId) {
    this.targetAccountId = targetAccountId;
    return this;
  }

  public UUID getSourceAccountId() {
    return sourceAccountId;
  }

  public void setSourceAccountId(UUID sourceAccountId) {
    this.sourceAccountId = sourceAccountId;
  }

  public static class Builder {

    private UUID sourceAccountId;
    private UUID targetAccountId;
    double amount;
    Date paymentDate;
    PaymentStatusEnum status;

    public Builder status(PaymentStatusEnum status) {
      this.status = status;
      return this;
    }

    public Builder paymentDate(Date paymentDate) {
      this.paymentDate = paymentDate;
      return this;
    }

    public Builder amount(double amount) {
      this.amount = amount;
      return this;
    }

    public Builder targetAccountId(UUID targetAccountId) {
      this.targetAccountId = targetAccountId;
      return this;
    }

    public Builder sourceAccountId(UUID sourceAccountId) {
      this.sourceAccountId = sourceAccountId;
      return this;
    }
  }
}
