package com.thomas.creditsimulator.customer.infrastructure;

import com.thomas.creditsimulator.customer.domain.Customer;
import com.thomas.creditsimulator.customer.domain.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findById(Long id);
    List<Customer> findByStatus(CustomerStatus status);
    Optional<Customer> findByDocumentNumber(String documentNumber);

}
