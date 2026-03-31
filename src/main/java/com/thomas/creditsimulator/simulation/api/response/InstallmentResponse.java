package com.thomas.creditsimulator.simulation.api.response;

import java.math.BigDecimal;

public record InstallmentResponse (
        Integer installmentNumber,
        BigDecimal monthlyPayment,
        BigDecimal interestPayment,
        BigDecimal principalPayment,
        BigDecimal remainingBalance
){
}
