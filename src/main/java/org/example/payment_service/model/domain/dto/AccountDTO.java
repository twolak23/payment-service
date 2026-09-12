package org.example.payment_service.model.domain.dto;

import org.example.payment_service.model.enums.BankProviderEnum;

import java.util.UUID;

public record AccountDTO (
   String iban,
   BankProviderEnum bankProvider,
   double balance,
   String cardNumber,
   String pin,
   UUID personId) {}
