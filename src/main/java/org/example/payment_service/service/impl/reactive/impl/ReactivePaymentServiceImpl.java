package org.example.payment_service.service.impl.reactive.impl;

import org.example.payment_service.model.domain.entity.reactive.AccountEntity;
import org.example.payment_service.model.domain.entity.reactive.PaymentEntity;
import org.example.payment_service.model.enums.PaymentStatusEnum;
import org.example.payment_service.model.rest.PaymentRequest;
import org.example.payment_service.model.rest.PaymentResponse;
import org.example.payment_service.repository.reactive.PaymentReactiveRepository;
import org.example.payment_service.service.impl.reactive.ReactiveAccountService;
import org.example.payment_service.service.impl.reactive.ReactivePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Date;

@Service
public class ReactivePaymentServiceImpl implements ReactivePaymentService {

  private final PaymentReactiveRepository repository;
  private final ReactiveAccountService accountService;

  @Autowired
  public ReactivePaymentServiceImpl(PaymentReactiveRepository repository, @Qualifier("realAccountReactive") ReactiveAccountService accountService) {
    this.repository = repository;
    this.accountService = accountService;
  }

  @Override
  @Transactional(transactionManager = "connectionFactoryTransactionManager")
  public Mono<PaymentResponse> pay(PaymentRequest request) {
    return accountService.getAccountByIban(request.getSourceAccountIban())
        .switchIfEmpty(Mono.error(new Exception()))
        .flatMap(sourceAccount ->
                accountService.getAccountByIban(request.getTargetAccountIban())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(targetAccount -> {
                      PaymentEntity entity = new PaymentEntity(sourceAccount, targetAccount, request.getAmount(), Date.from(Instant.now()), PaymentStatusEnum.ACCEPTED);
                      return repository.save(entity)
                          .flatMap(saved ->
                                  accountService.transfer(
                                      sourceAccount,
                                      targetAccount,
                                      request.getAmount()
                                  ).thenReturn(
                                      createResponse(saved, sourceAccount, targetAccount)
                                  )
                          );
                    })
        );
  }

  @Override
  public Flux<PaymentResponse> getAllPayments() {
    return repository.findAll().map(payment -> {
      PaymentResponse response = new PaymentResponse();
      response.setPaymentId(payment.getId());
      response.setPersonFrom(payment.getSourceAccount().getPerson().getFirstName() + " " + payment.getSourceAccount().getPerson().getLastName());
      response.setPersonTo(payment.getTargetAccount().getPerson().getFirstName() + " " + payment.getTargetAccount().getPerson().getLastName());
      response.setStatus(PaymentStatusEnum.ACCEPTED);
      return response;
    });
  }
  private PaymentResponse createResponse(PaymentEntity entity, AccountEntity sourceAccount, AccountEntity targetAccount) {
    PaymentResponse response = new PaymentResponse();
    response.setPaymentId(entity.getId());
    response.setPersonFrom(sourceAccount.getPerson().getFirstName() + " " + sourceAccount.getPerson().getLastName());
    response.setPersonTo(targetAccount.getPerson().getFirstName() + " " + targetAccount.getPerson().getLastName());
    response.setStatus(PaymentStatusEnum.ACCEPTED);
    return response;
  }

  public PaymentReactiveRepository getRepository() {
    return repository;
  }
}
