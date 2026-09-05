package org.example.payment_service.model.domain.entity.reactive;

import org.example.payment_service.model.enums.PaymentStatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.*;
import java.util.Date;
import java.util.UUID;


@Table("payment_reactive")
public class PaymentEntity {

  @Id
  @Column
  private UUID id;

  // TODO: add relation to Account entity and not null constraint
  @Column(value = "source_account")
  private AccountEntity sourceAccount;

  @Column("target_account")
  private AccountEntity targetAccount;

  @Column("amount")
  private double amount;

  @Column("payment_date")
  private Date paymentDate;

  @Column("status")
  private PaymentStatusEnum status;

  public PaymentEntity() {
  }

  public PaymentEntity(AccountEntity sourceAccount, AccountEntity targetAccount, double amount, Date paymentDate, PaymentStatusEnum status) {
    this.sourceAccount = sourceAccount;
    this.targetAccount = targetAccount;
    this.amount = amount;
    this.paymentDate = paymentDate;
    this.status = status;
  }

  public PaymentEntity(AccountEntity sourceAccount, AccountEntity targetAccount, double amount, Date paymentDate) {
    this.sourceAccount = sourceAccount;
    this.targetAccount = targetAccount;
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

  public AccountEntity getTargetAccount() {
    return targetAccount;
  }

  public PaymentEntity setTargetAccount(AccountEntity targetAccount) {
    this.targetAccount = targetAccount;
    return this;
  }

  public AccountEntity getSourceAccount() {
    return sourceAccount;
  }

  public PaymentEntity setSourceAccount(AccountEntity sourceAccount) {
    this.sourceAccount = sourceAccount;
    return this;
  }

  public static class Builder {

    private AccountEntity sourceAccount;
    private AccountEntity targetAccount;
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

    public Builder targetAccount(AccountEntity targetAccount) {
      this.targetAccount = targetAccount;
      return this;
    }

    public Builder sourceAccount(AccountEntity sourceAccount) {
      this.sourceAccount = sourceAccount;
      return this;
    }
  }
}
