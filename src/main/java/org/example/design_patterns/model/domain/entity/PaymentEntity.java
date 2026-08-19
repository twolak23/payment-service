package org.example.design_patterns.model.domain.entity;

import jakarta.persistence.*;
import org.example.design_patterns.model.enums.PaymentStatusEnum;

import java.util.Date;
import java.util.UUID;

@Entity(name = "payment_v2")
public class PaymentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "source_account", referencedColumnName = "id", nullable = false)
  private AccountEntity sourceAccount;

  @ManyToOne
  @JoinColumn(name = "target_account", referencedColumnName = "id", nullable = false)
  private AccountEntity targetAccount;

  @Column(name = "amount", nullable = false)
  private double amount;

  @Column(name = "payment_date", nullable = false)
  private Date paymentDate;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
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
