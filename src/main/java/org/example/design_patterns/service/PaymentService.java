package org.example.design_patterns.service;

import org.example.design_patterns.model.domain.legacy.LegacyPaymentRequest;
import org.example.design_patterns.model.domain.legacy.LegacyPaymentResponse;

public interface PaymentService {

  public LegacyPaymentResponse pay(LegacyPaymentRequest request);
}
