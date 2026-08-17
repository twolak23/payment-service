package org.example.design_patterns.repository;

import org.example.design_patterns.model.domain.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AccountRepository extends CrudRepository<AccountEntity, UUID> {
  AccountEntity getAccountEntityById(UUID accountId);

  AccountEntity getAccountEntityByIban(String iban);

  boolean existsByIdAndPin(UUID id, String pin);
}
