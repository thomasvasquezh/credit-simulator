package com.thomas.creditsimulator.simulation.infrastructure;

import com.thomas.creditsimulator.simulation.domain.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SimulationRepository extends JpaRepository<Simulation, Long> {
    Optional<Simulation> findById(Long id);
}
