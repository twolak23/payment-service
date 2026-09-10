package org.example.payment_service.api.reactive;
import org.example.payment_service.model.rest.PaymentRequest;
import org.example.payment_service.model.rest.PaymentResponse;
import org.example.payment_service.service.impl.reactive.ReactivePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller for handling requests for payment
 * */
@RestController
@RequestMapping("/payment/reactive")
public class ReactivePaymentController {

  private final ReactivePaymentService service;

  @Autowired
  public ReactivePaymentController(ReactivePaymentService service) {
    this.service = service;
  }

  @PostMapping
  public Mono<PaymentResponse> pay(@RequestBody PaymentRequest request) {
    return service.pay(request);
  }

  @GetMapping("/all")
  public Flux<PaymentResponse> getAllPayments() {
    return service.getAllPayments();
  }

}
