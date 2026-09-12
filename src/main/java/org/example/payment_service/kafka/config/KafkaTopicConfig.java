package org.example.payment_service.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

  public static final String PAYMENT_CREATED_TOPIC = "payment.created";

  @Bean
  public NewTopic paymentCreatedTopic() {
    return TopicBuilder
            .name(PAYMENT_CREATED_TOPIC)
            .partitions(2)
            .replicas(1)
            .build();
  }
}
