package com.thomas.creditsimulator.simulation.api;

import com.thomas.creditsimulator.product.domain.Product;
import com.thomas.creditsimulator.simulation.api.response.SimulationResponse;
import com.thomas.creditsimulator.simulation.domain.Simulation;
import org.springframework.stereotype.Component;

@Component
public class SimulationMapper{
    public SimulationResponse toResponse(Simulation simulation){
        return new SimulationResponse(
                simulation.getId(),
                simulation.getProduct().getId(),
                simulation.getProduct().getName(),
                simulation.getDocumentType(),
                simulation.getDocumentNumber(),
                simulation.getFirstName(),
                simulation.getLastName(),
                simulation.getEmail(),
                simulation.getPhone(),
                simulation.getMonthlyIncome(),
                simulation.getAmount(),
                simulation.getTermMonths(),
                simulation.getInterestRate(),
                simulation.getMonthlyPayment(),
                simulation.getTotalInterest(),
                simulation.getTotalPayment(),
                simulation.getCreatedAt()
        );
    }
}
