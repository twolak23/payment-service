package org.example.payment_service.service.bank;

public interface LegacyBankService {
  String makePayment(String from, String to, double amount);
}
