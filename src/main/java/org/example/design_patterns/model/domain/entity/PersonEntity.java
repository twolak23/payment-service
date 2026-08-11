package org.example.design_patterns.model.domain.entity;

import jakarta.persistence.*;

import java.util.Collection;
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

  @OneToMany
  private Collection<AccountEntity> accounts;

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

  public Collection<AccountEntity> getAccounts() {
    return accounts;
  }

  public PersonEntity setAccounts(Collection<AccountEntity> accounts) {
    this.accounts = accounts;
    return this;
  }
}
