package org.example.payment_service.model.domain.dto;

import org.example.payment_service.model.enums.BankProviderEnum;
import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

public class AccountDTO {
  private String iban;

  private BankProviderEnum bankProvider;

  private double balance;

  private String cardNumber;

  private String pin;

  private UUID personId;
}
