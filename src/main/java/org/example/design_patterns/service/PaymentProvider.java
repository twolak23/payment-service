package org.example.design_patterns.service;

import org.example.design_patterns.domain.PaymentRequest;
import org.example.design_patterns.domain.PaymentResponse;

public interface PaymentProvider {
  PaymentResponse makePayment(PaymentRequest request);
}
