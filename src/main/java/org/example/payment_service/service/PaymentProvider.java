package org.example.payment_service.service;

import org.example.payment_service.model.rest.PaymentRequest;
import org.example.payment_service.model.rest.PaymentResponse;

public interface PaymentProvider {
  PaymentResponse makePayment(PaymentRequest request);
}
