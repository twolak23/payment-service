package org.example.design_patterns.domain;

import jakarta.persistence.*;
import org.example.design_patterns.model.PaymentStatusEnum;

import java.util.UUID;

@Entity(name = "person")
public class PersonEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column
  private UUID id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private PaymentStatusEnum status;

  public PersonEntity(String firstName, String lastName, PaymentStatusEnum status) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.status = status;
  }

  public PersonEntity() {

  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public PaymentStatusEnum getStatus() {
    return status;
  }

  public void setStatus(PaymentStatusEnum status) {
    this.status = status;
  }
}
