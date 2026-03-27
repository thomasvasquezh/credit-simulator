package com.thomas.creditsimulator.customer.api.response;

import com.thomas.creditsimulator.customer.domain.CustomerStatus;
import com.thomas.creditsimulator.customer.domain.DocumentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CustomerResponse(
        Long id,
        DocumentType documentType,
        String documentNumber,
        String firstName,
        String lastName,
        String email,
        String phone,
        BigDecimal monthlyIncome,
        CustomerStatus status,
        LocalDateTime createdAt
) {
}