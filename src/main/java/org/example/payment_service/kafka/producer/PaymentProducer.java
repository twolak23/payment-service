package org.example.payment_service.kafka.producer;

import org.example.payment_service.kafka.config.KafkaTopicConfig;
import org.example.payment_service.kafka.events.PaymentCompletedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {
  private final KafkaTemplate<String, PaymentCompletedEvent> kafkaTemplate;

  public PaymentProducer(KafkaTemplate<String, PaymentCompletedEvent> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }
  public void publish(PaymentCompletedEvent event) {
    kafkaTemplate.send(KafkaTopicConfig.PAYMENT_CREATED_TOPIC,
            event.paymentId().toString(),
            event
    );
  }
}
