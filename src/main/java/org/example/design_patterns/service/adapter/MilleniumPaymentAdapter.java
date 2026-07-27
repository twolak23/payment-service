package org.example.design_patterns.service.adapter;

import org.example.design_patterns.domain.PaymentRequest;
import org.example.design_patterns.domain.PaymentResponse;
import org.example.design_patterns.model.PaymentStatusEnum;
import org.example.design_patterns.service.PaymentProvider;
import org.example.design_patterns.service.impl.bank.MillenniumLegacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MilleniumPaymentAdapter implements PaymentProvider {

  private final MillenniumLegacyService legacyService;

  @Autowired
  public MilleniumPaymentAdapter(MillenniumLegacyService legacyService) {
    this.legacyService = legacyService;
  }


  @Override
  public PaymentResponse makePayment(PaymentRequest request) {
    String result = legacyService.makePayment(
            request.getPersonFrom(),
            request.getPersonTo(),
            request.getAmount()
    );
    return new PaymentResponse(request.getPersonFrom(), request.getPersonTo(), request.getAmount(), PaymentStatusEnum.ACCEPTED);
  }
}
