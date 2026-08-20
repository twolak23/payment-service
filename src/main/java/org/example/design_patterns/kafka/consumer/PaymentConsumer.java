package org.example.design_patterns.kafka.consumer;

import org.example.design_patterns.kafka.config.KafkaTopicConfig;
import org.example.design_patterns.kafka.events.PaymentCompletedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {
  private KafkaTemplate<String, PaymentCompletedEvent> kafkaTemplate;

  @KafkaListener(
          topics = KafkaTopicConfig.PAYMENT_CREATED_TOPIC,
          groupId = "payment-consumer-group-demo"
  )
  public void consume(PaymentCompletedEvent event) {
    System.out.println("Received payment event: " + event);
  }
}
