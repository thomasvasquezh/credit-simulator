package com.thomas.creditsimulator.simulation.api.request;

import com.thomas.creditsimulator.customer.domain.DocumentType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateSimulationRequest (
        @NotNull
        Long productId,

        @NotNull
        DocumentType documentType,

        @NotBlank
        String documentNumber,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @Email
        @NotBlank
        String email,

        @Pattern(regexp = "^[0-9]{9}$", message = "Phone must have 9 digits")
        @NotBlank
        String phone,

        @NotNull
        @Positive
        BigDecimal monthlyIncome,

        @NotNull
        @Positive
        BigDecimal amount,

        @NotNull
        @Positive
        Integer termMonths
) {
}
