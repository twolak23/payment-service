package org.example.payment_service.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@Converter(autoApply = false) // apply false to only encrypt where converter is applied
public class EncryptionConverter implements AttributeConverter<String, String> {

  @Override
  public String convertToDatabaseColumn(String attribute) {
    if (attribute == null) {
      return null;
    }
    return Base64.getEncoder().encodeToString(attribute.getBytes());
  }

  @Override
  public String convertToEntityAttribute(String dbData) {
    if (dbData == null) {
      return null;
    }
    return new String(Base64.getDecoder().decode(dbData));
  }
}