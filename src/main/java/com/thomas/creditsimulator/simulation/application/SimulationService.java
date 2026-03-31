package com.thomas.creditsimulator.simulation.application;

import com.thomas.creditsimulator.common.BusinessRuleException;
import com.thomas.creditsimulator.common.NotFoundException;
import com.thomas.creditsimulator.product.domain.Product;
import com.thomas.creditsimulator.product.domain.ProductStatus;
import com.thomas.creditsimulator.product.infrastructure.ProductRepository;
import com.thomas.creditsimulator.simulation.api.SimulationMapper;
import com.thomas.creditsimulator.simulation.api.response.InstallmentResponse;
import com.thomas.creditsimulator.simulation.api.response.SimulationScheduleResponse;
import com.thomas.creditsimulator.simulation.application.calculator.CalculationResult;
import com.thomas.creditsimulator.simulation.application.calculator.LoanCalculator;
import com.thomas.creditsimulator.simulation.application.calculator.ScheduleCalculator;
import com.thomas.creditsimulator.simulation.infrastructure.SimulationRepository;
import com.thomas.creditsimulator.simulation.api.request.CreateSimulationRequest;
import com.thomas.creditsimulator.simulation.api.response.SimulationResponse;
import com.thomas.creditsimulator.simulation.domain.Simulation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SimulationService {
    private final SimulationRepository simulationRepository;
    private final ProductRepository productRepository;
    private final LoanCalculator loanCalculator;
    private final SimulationMapper simulationMapper;
    private final ScheduleCalculator scheduleCalculator;

    public SimulationResponse createSimulation(CreateSimulationRequest request){

        Product product = productRepository.findByIdAndStatus(request.productId(), ProductStatus.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Active product not found with ID: " + request.productId()));

        validateSimulationRules(product, request);

        CalculationResult calculation = loanCalculator.calculate(
                request.amount(),
                request.termMonths(),
                product.getInterestRate()
        );

        Simulation simulation = Simulation.builder()
                .product(product)
                .documentType(request.documentType())
                .documentNumber(request.documentNumber())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phone(request.phone())
                .monthlyIncome(request.monthlyIncome())
                .amount(request.amount())
                .termMonths(request.termMonths())
                .interestRate(product.getInterestRate())
                .monthlyPayment(calculation.monthlyPayment())
                .totalInterest(calculation.totalInterest())
                .totalPayment(calculation.totalPayment())
                .build();

        Simulation savedSimulation = simulationRepository.save(simulation);

        return new SimulationResponse(
                savedSimulation.getId(),
                savedSimulation.getProduct().getId(),
                product.getName(),
                savedSimulation.getDocumentType(),
                savedSimulation.getDocumentNumber(),
                savedSimulation.getFirstName(),
                savedSimulation.getLastName(),
                savedSimulation.getEmail(),
                savedSimulation.getPhone(),
                savedSimulation.getMonthlyIncome(),
                savedSimulation.getAmount(),
                savedSimulation.getTermMonths(),
                savedSimulation.getInterestRate(),
                savedSimulation.getMonthlyPayment(),
                savedSimulation.getTotalInterest(),
                savedSimulation.getTotalPayment(),
                savedSimulation.getCreatedAt()
        );
    }

    public List<SimulationResponse> getAllSimulations() {
        return simulationRepository.findAll().stream()
                .map(simulationMapper::toResponse)
                .toList();
    }

    public SimulationResponse getSimulationById(Long id) {
        Simulation simulation = simulationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Simulation not found with ID: " + id));
        return simulationMapper.toResponse(simulation);
    }

    private void validateSimulationRules(Product product, CreateSimulationRequest request) {
        if (request.amount().compareTo(product.getMinAmount()) < 0 ||
                request.amount().compareTo(product.getMaxAmount()) > 0) {
            throw new BusinessRuleException("Requested amount must be between " + product.getMinAmount() +
                    " and " + product.getMaxAmount());
        }

        if (request.termMonths() < product.getMinTermMonths() ||
                request.termMonths() > product.getMaxTermMonths()) {
            throw new BusinessRuleException( "Requested term must be between " + product.getMinTermMonths() +
                    " and " + product.getMaxTermMonths() + " months");
        }
    }

    public SimulationScheduleResponse getSimulationSchedule(Long id){
        Simulation simulation = simulationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Simulation not found with ID: " + id));

        List<InstallmentResponse> installments =scheduleCalculator.generateSchedule(simulation);

        return new SimulationScheduleResponse(
                simulation.getId(),
                simulation.getAmount(),
                simulation.getTermMonths(),
                simulation.getInterestRate(),
                simulation.getMonthlyPayment(),
                installments
        );
    }

}
