package org.example.design_patterns.service.impl.bank;

import org.example.design_patterns.service.bank.LegacyBankService;
import org.springframework.stereotype.Service;

@Service
public class MillenniumLegacyService implements LegacyBankService {

  @Override
  public String makePayment(String from, String to, double amount) {
    System.out.printf("The payment of %f is made from %s to %s%n by Bank Millennium", amount, from, to);
    return "";
  }
}
