package com.thomas.creditsimulator.simulation.application.calculator;
import com.thomas.creditsimulator.product.domain.Product;
import com.thomas.creditsimulator.simulation.api.request.CreateSimulationRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Component
public class LoanCalculator {

public CalculationResult calculate(BigDecimal amount, Integer termMonths, BigDecimal annualRate) {

    validateInput(amount, termMonths, annualRate);

    if (annualRate.compareTo(BigDecimal.ZERO) == 0) {
        BigDecimal monthlyPayment = amount.divide(BigDecimal.valueOf(termMonths), 2, RoundingMode.HALF_UP);
        BigDecimal totalPayment = monthlyPayment.multiply(BigDecimal.valueOf(termMonths));
        BigDecimal totalInterest = BigDecimal.ZERO;

        return new CalculationResult(monthlyPayment, totalInterest, totalPayment);
    }

    BigDecimal monthlyRate = annualRate.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

    BigDecimal onePlusRate = BigDecimal.ONE.add(monthlyRate);
    BigDecimal power = onePlusRate.pow(termMonths, MathContext.DECIMAL64);

    BigDecimal numerator = amount.multiply(monthlyRate).multiply(power);
    BigDecimal denominator = power.subtract(BigDecimal.ONE);

    BigDecimal monthlyPayment = numerator.divide(denominator, 2, RoundingMode.HALF_UP);
    BigDecimal totalPayment = monthlyPayment.multiply(BigDecimal.valueOf(termMonths));
    BigDecimal totalInterest = totalPayment.subtract(amount);

        return new CalculationResult(monthlyPayment, totalInterest, totalPayment);
    }

    private void validateInput(BigDecimal amount, Integer termMonths, BigDecimal annualRate) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Loan amount must be greater than zero");
        }

        if (termMonths == null || termMonths <= 0) {
            throw new IllegalArgumentException("Term months must be greater than zero");
        }

        if (annualRate == null || annualRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Annual rate cannot be negative");
        }
    }
}
