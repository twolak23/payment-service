package org.example.design_patterns.service.bank;

import org.example.design_patterns.domain.PersonEntity;
import org.example.design_patterns.model.PaymentStatusEnum;

public interface BankService {

  PaymentStatusEnum makePayment(PersonEntity from, PersonEntity to, double amount);
}
