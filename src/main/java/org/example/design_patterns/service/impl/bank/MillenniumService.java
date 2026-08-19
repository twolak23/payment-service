package org.example.design_patterns.service.impl.bank;

import org.example.design_patterns.model.domain.entity.PersonEntity;
import org.example.design_patterns.model.enums.PaymentStatusEnum;
import org.example.design_patterns.service.bank.BankService;
import org.springframework.stereotype.Service;

@Service
public class MillenniumService implements BankService {

  @Override
  public PaymentStatusEnum makePayment(PersonEntity from, PersonEntity to, double amount) {
    return null;
  }
}
