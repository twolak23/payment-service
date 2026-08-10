package org.example.design_patterns.service.impl;

import org.example.design_patterns.domain.legacy.LegacyPaymentEntity;
import org.example.design_patterns.domain.legacy.LegacyPaymentRequest;
import org.example.design_patterns.domain.legacy.LegacyPaymentResponse;
import org.example.design_patterns.repository.PaymentRepository;
import org.example.design_patterns.service.PaymentProvider;
import org.example.design_patterns.service.PaymentService;
import org.example.design_patterns.service.discounter.Discounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Service
public class PaymentServiceImpl implements PaymentService {

  private final PaymentRepository repository;
  private final PaymentProvider paymentProvider;

  @Autowired
  public PaymentServiceImpl(PaymentRepository repository, PaymentProvider paymentProvider) {
    this.repository = repository;
    this.paymentProvider = paymentProvider;
  }

  // To reduce code verbosity for strategy, use lambda expressions instead of implemented classes
  Discounter christmasDiscounter = amount -> amount * 0.85;
  Discounter easterDiscounter = amount -> amount * 0.5;


  @Override
  @Transactional
  public LegacyPaymentResponse pay(LegacyPaymentRequest request) {
    request.setAmount(applyDiscount(request.getAmount()));

    LegacyPaymentEntity entity = new LegacyPaymentEntity(request.getPersonFrom(), request.getPersonTo(), request.getAmount(), Date.from(Instant.now()));
    try {
      repository.save(entity);
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }

    return null;
  }

  private double applyDiscount(double amount) {
    // date for christmas discount
//    LocalDate payDate = LocalDate.of(2026, 12, 10);
    // date for easter discount
    LocalDate payDate = LocalDate.of(2026, 3, 26);
    switch (discountApplicable(payDate)) {
      case "christmas":
        return christmasDiscounter.applyDiscount(amount);

        // Better way is to move them into Discounter interface itself
//        return Discounter.ChristmasDiscounter().applyDiscount(amount);
      case "easter":
        return easterDiscounter.applyDiscount(amount);

      // Better way is to move them into Discounter interface itself
//        return Discounter.EasterDiscounter().applyDiscount(amount);
      default:
        return amount;
    }
  }
  /** Method for determining the type of discount */
  private String discountApplicable(LocalDate purchaseDate) {
    if(purchaseDate.isAfter(LocalDate.of(2026, 12,1)) && purchaseDate.isBefore(LocalDate.of(2026, 12, 31))) {
      return "christmas";
    }
    else if (purchaseDate.isAfter(LocalDate.of(2026, 3,1)) && purchaseDate.isBefore(LocalDate.of(2026, 4, 30))) {
      return "easter";
    }
    return "";
  }
  public PaymentRepository getRepository() {
    return repository;
  }
}
