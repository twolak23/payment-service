package org.example.design_patterns.service;

import org.example.design_patterns.model.domain.legacy.LegacyPaymentRequest;
import org.example.design_patterns.model.domain.legacy.LegacyPaymentResponse;
import org.example.design_patterns.model.rest.PaymentRequest;
import org.example.design_patterns.model.rest.PaymentResponse;

public interface PaymentService {

  public PaymentResponse pay(PaymentRequest request);
}
