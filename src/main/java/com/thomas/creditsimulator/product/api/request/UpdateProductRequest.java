package com.thomas.creditsimulator.product.api.request;

import com.thomas.creditsimulator.product.domain.ProductType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UpdateProductRequest(
    @NotBlank
    String name,
    @NotNull
    ProductType type,
    @NotNull
    @Positive
    BigDecimal minAmount,
    @NotNull
    @Positive
    BigDecimal maxAmount,
    @NotNull
    @Positive
    Integer minTermMonths,
    @NotNull
    @Positive
    Integer maxTermMonths,
    @NotNull
    @DecimalMin(value = "0.0001", inclusive = true) BigDecimal interestRate
){
}

