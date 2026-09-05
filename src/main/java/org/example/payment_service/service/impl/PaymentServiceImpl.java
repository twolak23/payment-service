package org.example.payment_service.service.impl;

import org.example.payment_service.model.domain.entity.jpa.AccountEntity;
import org.example.payment_service.model.domain.entity.jpa.PaymentEntity;
import org.example.payment_service.model.enums.PaymentStatusEnum;
import org.example.payment_service.model.rest.PaymentRequest;
import org.example.payment_service.model.rest.PaymentResponse;
import org.example.payment_service.repository.jpa.PaymentRepository;
import org.example.payment_service.repository.reactive.PaymentReactiveRepository;
import org.example.payment_service.service.AccountService;
import org.example.payment_service.service.PaymentProvider;
import org.example.payment_service.service.PaymentService;
import org.example.payment_service.service.discounter.Discounter;
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
  private final PaymentReactiveRepository reactiveRepository;
  private final AccountService accountService;
  private final PaymentProvider paymentProvider;

  @Autowired
  public PaymentServiceImpl(PaymentRepository repository, PaymentReactiveRepository reactiveRepository, @Qualifier("realAccount") AccountService accountService, PaymentProvider paymentProvider) {
    this.repository = repository;
    this.reactiveRepository = reactiveRepository;
    this.accountService = accountService;
    this.paymentProvider = paymentProvider;
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
