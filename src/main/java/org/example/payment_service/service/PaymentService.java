package org.example.payment_service.service;


import org.example.design_patterns.model.rest.PaymentRequest;
import org.example.design_patterns.model.rest.PaymentResponse;
import org.example.payment_service.model.rest.PaymentRequest;
import org.example.payment_service.model.rest.PaymentResponse;

public interface PaymentService {

  PaymentResponse pay(PaymentRequest request);
}
