package com.thomas.creditsimulator.simulation.api.response;

import com.thomas.creditsimulator.customer.domain.DocumentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SimulationResponse(
        Long id,
        Long productId,
        String productName,
        DocumentType documentType,
        String documentNumber,
        String firstName,
        String lastName,
        String email,
        String phone,
        BigDecimal monthlyIncome,
        BigDecimal amount,
        Integer termMonths,
        BigDecimal interestRate,
        BigDecimal monthlyPayment,
        BigDecimal totalInterest,
        BigDecimal totalPayment,
        LocalDateTime createdAt
) {
}
