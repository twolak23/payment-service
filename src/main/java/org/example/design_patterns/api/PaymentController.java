package org.example.design_patterns.api;
import org.example.design_patterns.model.rest.PaymentRequest;
import org.example.design_patterns.model.rest.PaymentResponse;
import org.example.design_patterns.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling requests for payment
 * */
@RestController
@RequestMapping("/payment")
public class PaymentController {

  private final PaymentService service;

  @Autowired
  public PaymentController(PaymentService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<PaymentResponse> pay(@RequestBody PaymentRequest request) {

    return new ResponseEntity<>(service.pay(request), HttpStatus.CREATED);
  }

}
