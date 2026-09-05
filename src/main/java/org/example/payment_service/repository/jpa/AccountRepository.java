package org.example.payment_service.repository.jpa;

import org.example.payment_service.model.domain.entity.jpa.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, UUID> {
  AccountEntity getAccountEntityById(UUID accountId);

  AccountEntity getAccountEntityByIban(String iban);

  boolean existsByIdAndPin(UUID id, String pin);
}
