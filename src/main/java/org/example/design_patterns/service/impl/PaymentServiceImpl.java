package org.example.design_patterns.service.impl;

import org.example.design_patterns.kafka.events.PaymentCompletedEvent;
import org.example.design_patterns.kafka.producer.PaymentProducer;
import org.example.design_patterns.model.domain.entity.AccountEntity;
import org.example.design_patterns.model.domain.entity.PaymentEntity;
import org.example.design_patterns.model.enums.PaymentStatusEnum;
import org.example.design_patterns.model.rest.PaymentRequest;
import org.example.design_patterns.model.rest.PaymentResponse;
import org.example.design_patterns.repository.PaymentRepository;
import org.example.design_patterns.service.AccountService;
import org.example.design_patterns.service.PaymentService;
import org.example.design_patterns.service.discounter.Discounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Service
public class PaymentServiceImpl implements PaymentService {

  private final PaymentRepository repository;
  private final AccountService accountService;
  private final PaymentProducer producer;

  @Autowired
  public PaymentServiceImpl(PaymentRepository repository, @Qualifier("realAccount") AccountService accountService, PaymentProducer producer) {
    this.repository = repository;
    this.accountService = accountService;
    this.producer = producer;
  }

  // To reduce code verbosity for strategy, use lambda expressions instead of implemented classes
  Discounter christmasDiscounter = amount -> amount * 0.85;
  Discounter easterDiscounter = amount -> amount * 0.5;


  @Override
  @Transactional
  public PaymentResponse pay(PaymentRequest request) {

    AccountEntity sourceAccount = accountService.getAccountByIban(request.getSourceAccountIban());
    AccountEntity targetAccount = accountService.getAccountByIban(request.getTargetAccountIban());

    PaymentEntity entity = new PaymentEntity(sourceAccount, targetAccount, request.getAmount(), Date.from(Instant.now()), PaymentStatusEnum.ACCEPTED);
    try {
      entity = repository.save(entity);
      PaymentResponse response = new PaymentResponse();
      response.setPaymentId(entity.getId());
      response.setPersonFrom(sourceAccount.getPerson().getFirstName() + " " + sourceAccount.getPerson().getLastName());
      response.setPersonTo(targetAccount.getPerson().getFirstName() + " " + targetAccount.getPerson().getLastName());
      response.setStatus(PaymentStatusEnum.ACCEPTED);
      accountService.transfer(sourceAccount, targetAccount, request.getAmount());
      producer.publish(new PaymentCompletedEvent(entity.getId(),sourceAccount.getId(), targetAccount.getId(), entity.getAmount(), response.getStatus()));
      return response;
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
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
