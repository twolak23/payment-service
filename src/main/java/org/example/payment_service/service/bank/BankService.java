package org.example.payment_service.service.bank;

import org.example.payment_service.model.domain.entity.jpa.PersonEntity;
import org.example.payment_service.model.enums.PaymentStatusEnum;

public interface BankService {

  PaymentStatusEnum makePayment(PersonEntity from, PersonEntity to, double amount);
}
