package org.example.payment_service.kafka.events;

import org.example.payment_service.model.enums.PaymentStatusEnum;

import java.util.UUID;

public record PaymentCompletedEvent(
        UUID paymentId,
        String sourceEmail,
        String targetEmail,
        double amount,
        PaymentStatusEnum paymentStatus
) {}
