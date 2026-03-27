package com.thomas.creditsimulator.simulation.api;


import com.thomas.creditsimulator.simulation.api.request.CreateSimulationRequest;
import com.thomas.creditsimulator.simulation.api.response.SimulationResponse;
import com.thomas.creditsimulator.simulation.application.SimulationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/simulations")
@RequiredArgsConstructor
public class SimulationController {
    private final SimulationService simulationService;

    @PostMapping
    public ResponseEntity<SimulationResponse> createSimulation(
            @Valid @RequestBody CreateSimulationRequest request) {
            SimulationResponse response = simulationService.createSimulation(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

    @GetMapping
    public ResponseEntity<List<SimulationResponse>> getAllSimulations() {
        List<SimulationResponse> simulations = simulationService.getAllSimulations();
        return ResponseEntity.ok(simulations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimulationResponse> getSimulationById(@PathVariable Long id) {
        SimulationResponse simulation = simulationService.getSimulationById(id);
        return ResponseEntity.ok(simulation);
    }

    }

