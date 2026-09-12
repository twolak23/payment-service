package org.example.payment_service.api.reactive;

import jakarta.websocket.server.PathParam;
import org.example.payment_service.model.rest.AccountDetailsResponse;
import org.example.payment_service.service.impl.reactive.ReactiveAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/accounts/reactive")
public class ReactiveAccountController {

  private final ReactiveAccountService service;
  @Autowired
  ReactiveAccountController(@Qualifier("accountProxyReactive") ReactiveAccountService service) {
    this.service = service;
  }


  /**
   * Function returning the details of account, if found by its id. The data are masked or not, depends on if the PIN is valid or not.
   * @param id id of an account to search for.
   * @param pin NOTE: PIN is used only for demo purposes.
   *            In a real system, access control should be based on authenticated user context, roles, permissions or token-based authorization.
   * @return Details of a found account, which can be masked, depending on the result of PIN authentication.
   */
  @PostMapping("/details/{id}")
  public Mono<AccountDetailsResponse> getAccountDetails(@PathVariable @PathParam("id") String id, @RequestBody(required = false) String pin) {
    return this.service.getAccountDetails(UUID.fromString(id), pin);
  }

}
