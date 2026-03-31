package com.thomas.creditsimulator.simulation.api.response;

import java.math.BigDecimal;
import java.util.List;

public record SimulationScheduleResponse(
        Long simulationId,
        BigDecimal amount,
        Integer termMonths,
        BigDecimal interestRate,
        BigDecimal monthlyPayment,
        List<InstallmentResponse> installments
) {
}
