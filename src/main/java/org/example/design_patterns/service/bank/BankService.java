package org.example.design_patterns.service.bank;

import org.example.design_patterns.model.domain.entity.PersonEntity;
import org.example.design_patterns.model.enums.PaymentStatusEnum;

public interface BankService {

  PaymentStatusEnum makePayment(PersonEntity from, PersonEntity to, double amount);
}
