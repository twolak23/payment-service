package org.example.payment_service.api;

import jakarta.websocket.server.PathParam;
import org.example.payment_service.model.rest.AccountDetailsResponse;
import org.example.payment_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private final AccountService service;
  @Autowired
  AccountController(@Qualifier("accountProxy") AccountService service) {
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
  public ResponseEntity<AccountDetailsResponse> getAccountDetails(@PathVariable @PathParam("id") String id, @RequestParam("pin") String pin) {
    try {
      AccountDetailsResponse response = this.service.getAccountDetails(UUID.fromString(id), pin);
      if(response == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(response, HttpStatus.FOUND);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
