package org.example.design_patterns.domain;

import jakarta.persistence.*;

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

  @Column(name = "amount", nullable = false)
  private double amount;

  @Column(name = "payment_date", nullable = false)
  private Date paymentDate;

  public PaymentEntity() {
  }

  public PaymentEntity(String personFromName, String personToName, double amount, Date paymentDate) {
    this.personFromName = personFromName;
    this.personToName = personToName;
    this.amount = amount;
    this.paymentDate = paymentDate;
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
}
