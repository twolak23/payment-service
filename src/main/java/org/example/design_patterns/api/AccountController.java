package org.example.design_patterns.api;

import jakarta.websocket.server.PathParam;
import org.example.design_patterns.model.rest.AccountDetailsResponse;
import org.example.design_patterns.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private final AccountService service;
  @Autowired
  AccountController(AccountService service) {
    this.service = service;
  }

  @PostMapping("/details/{id}")
  public ResponseEntity<AccountDetailsResponse> getAccountDetails(@PathVariable @PathParam("id") String id) {
    try {
      AccountDetailsResponse response = this.service.getAccountDetails(UUID.fromString(id));
      return new ResponseEntity<>(response, HttpStatus.FOUND);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
