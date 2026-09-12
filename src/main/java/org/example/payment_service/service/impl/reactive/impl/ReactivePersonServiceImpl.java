package org.example.payment_service.service.impl.reactive.impl;

import org.example.payment_service.model.domain.dto.PersonDTO;
import org.example.payment_service.repository.reactive.PersonReactiveRepository;
import org.example.payment_service.service.impl.reactive.ReactivePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ReactivePersonServiceImpl implements ReactivePersonService {

  private PersonReactiveRepository repository;

  @Autowired
  public ReactivePersonServiceImpl(PersonReactiveRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<PersonDTO> getPersonById(UUID id) {
    return repository.findById(id)
        .switchIfEmpty(Mono.error(new Exception()))
        .map(person -> new PersonDTO(person.getFirstName(), person.getLastName()));
  }
}
