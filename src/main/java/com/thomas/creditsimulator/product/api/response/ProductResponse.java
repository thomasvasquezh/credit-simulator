package com.thomas.creditsimulator.product.api.response;

import com.thomas.creditsimulator.product.domain.ProductStatus;
import com.thomas.creditsimulator.product.domain.ProductType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String name,
        ProductType type,
        BigDecimal minAmount,
        BigDecimal maxAmount,
        Integer minTermMonths,
        Integer maxTermMonths,
        BigDecimal interestRate,
        ProductStatus status,
        LocalDateTime createdAt
) {
}
