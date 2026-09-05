package org.example.payment_service.service.adapter;

import org.example.payment_service.model.rest.PaymentRequest;
import org.example.payment_service.model.rest.PaymentResponse;
import org.example.payment_service.model.enums.PaymentStatusEnum;
import org.example.payment_service.service.PaymentProvider;
import org.example.payment_service.service.impl.bank.MillenniumLegacyService;
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
            request.getSourceAccountIban(),
            request.getTargetAccountIban(),
            request.getAmount()
    );
    return new PaymentResponse(request.getSourceAccountIban(), request.getTargetAccountIban(), PaymentStatusEnum.ACCEPTED);
  }
}
