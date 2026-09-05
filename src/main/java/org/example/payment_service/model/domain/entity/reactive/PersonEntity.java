package org.example.payment_service.model.domain.entity.reactive;

import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.*;
import org.springframework.data.annotation.Id;
import org.example.payment_service.model.domain.dto.AccountDTO;

import java.util.Collection;
import java.util.UUID;

@Table(name = "person")
public class PersonEntity {
  @Id
  @Column
  private UUID id;

  @Column("first_name")
  private String firstName;

  @Column("last_name")
  private String lastName;

  @Transient
  private Collection<AccountDTO> accounts;

  public PersonEntity(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public PersonEntity() {}

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

  public Collection<AccountDTO> getAccounts() {
    return accounts;
  }

  public void setAccounts(Collection<AccountDTO> accounts) {
    this.accounts = accounts;
  }
}
