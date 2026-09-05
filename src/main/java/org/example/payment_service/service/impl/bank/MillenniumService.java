package org.example.payment_service.service.impl.bank;

import org.example.payment_service.model.domain.entity.jpa.PersonEntity;
import org.example.payment_service.model.enums.PaymentStatusEnum;
import org.example.payment_service.service.bank.BankService;
import org.springframework.stereotype.Service;

@Service
public class MillenniumService implements BankService {

  @Override
  public PaymentStatusEnum makePayment(PersonEntity from, PersonEntity to, double amount) {
    return null;
  }
}
