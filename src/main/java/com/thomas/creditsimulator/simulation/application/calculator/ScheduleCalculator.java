package com.thomas.creditsimulator.simulation.application.calculator;

import com.thomas.creditsimulator.simulation.api.response.InstallmentResponse;
import com.thomas.creditsimulator.simulation.domain.Simulation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
@Component
public class ScheduleCalculator {

    public List<InstallmentResponse> generateSchedule(Simulation simulation){
         List<InstallmentResponse> installments = new ArrayList<>();
        BigDecimal remainingBalance = simulation.getAmount();
        BigDecimal monthlyRate = simulation.getInterestRate()
                .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

        for (int installmentNumber = 1; installmentNumber <= simulation.getTermMonths(); installmentNumber++) {
            BigDecimal interestPayment = remainingBalance.multiply(monthlyRate)
                    .setScale(2, RoundingMode.HALF_UP);

            BigDecimal principalPayment = simulation.getMonthlyPayment().subtract(interestPayment)
                    .setScale(2, RoundingMode.HALF_UP);

            remainingBalance = remainingBalance.subtract(principalPayment)
                    .setScale(2, RoundingMode.HALF_UP);

            if (remainingBalance.compareTo(BigDecimal.ZERO) < 0) {
                remainingBalance = BigDecimal.ZERO;
            }
            installments.add(new InstallmentResponse(
                    installmentNumber,
                    simulation.getMonthlyPayment(),
                    interestPayment,
                    principalPayment,
                    remainingBalance
            ));
        }

        return installments;
    }

}
