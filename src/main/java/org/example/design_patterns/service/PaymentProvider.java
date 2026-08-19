package org.example.design_patterns.service;

import org.example.design_patterns.model.rest.PaymentRequest;
import org.example.design_patterns.model.rest.PaymentResponse;

public interface PaymentProvider {
  PaymentResponse makePayment(PaymentRequest request);
}
