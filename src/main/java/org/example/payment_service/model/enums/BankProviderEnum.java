package org.example.payment_service.model.enums;

import java.util.Arrays;
import java.util.List;

public enum BankProviderEnum {

  PKO("PKO", "PKO BP"),
  MILLENNIUM("Millennium", "Bank Millennium"),
  REVOLUT("Revolut", "Revolut");

  private final String name;
  private final String description;

  BankProviderEnum(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public BankProviderEnum getByDescription(String description){

    List<BankProviderEnum> foundEnums = Arrays.stream(BankProviderEnum.values()).filter(enumValue -> enumValue.getDescription().equals(description)).toList();
    if(foundEnums.size() == 1) {
      return foundEnums.getFirst();
    }
    return null;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }
}
