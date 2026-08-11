package org.example.design_patterns.domain;

import jakarta.persistence.*;
import org.example.design_patterns.model.PaymentStatusEnum;

import java.util.Date;
import java.util.UUID;

@Entity(name = "payment_v2")
public class PaymentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "person_from_name", nullable = false)
  private String personFromName;

  @Column(name = "person_to_name", nullable = false)
  private String personToName;

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

  public PaymentEntity(String personFromName, String personToName, AccountEntity sourceAccount, AccountEntity targetAccount, double amount, Date paymentDate, PaymentStatusEnum status) {
    this.personFromName = personFromName;
    this.personToName = personToName;
    this.sourceAccount = sourceAccount;
    this.targetAccount = targetAccount;
    this.amount = amount;
    this.paymentDate = paymentDate;
    this.status = status;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getPersonFromName() {
    return personFromName;
  }

  public void setPersonFromName(String personFromName) {
    this.personFromName = personFromName;
  }

  public String getPersonToName() {
    return personToName;
  }

  public void setPersonToName(String personToName) {
    this.personToName = personToName;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }
  public static class Builder {
    private String personFromName;
    private String personToName;
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

    public Builder personToName(String personToName) {
      this.personToName = personToName;
      return this;
    }

    public Builder personFromName(String personFromName) {
      this.personFromName = personFromName;
      return this;
    }
  }
}
