package org.example.design_patterns.kafka.events;

import org.example.design_patterns.model.enums.PaymentStatusEnum;

import java.util.UUID;

public record PaymentCompletedEvent(
        UUID paymentId,
        String sourceEmail,
        String targetEmail,
        double amount,
        PaymentStatusEnum paymentStatus
) {}
