package com.thomas.creditsimulator.simulation.application.calculator;

import java.math.BigDecimal;

public record CalculationResult (
        BigDecimal monthlyPayment,
        BigDecimal totalInterest,
        BigDecimal totalPayment
){
}
