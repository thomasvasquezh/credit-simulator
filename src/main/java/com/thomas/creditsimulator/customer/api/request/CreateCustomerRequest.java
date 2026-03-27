package com.thomas.creditsimulator.customer.api.request;

import com.thomas.creditsimulator.customer.domain.DocumentType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateCustomerRequest(

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

        @NotBlank
        String phone,

        @NotNull
        @Positive
        BigDecimal monthlyIncome
){
}