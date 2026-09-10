package org.example.payment_service.service.impl.reactive;

import org.example.payment_service.model.rest.PaymentRequest;
import org.example.payment_service.model.rest.PaymentResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactivePaymentService {
  Mono<PaymentResponse> pay(PaymentRequest request);
  Flux<PaymentResponse> getAllPayments();
}
