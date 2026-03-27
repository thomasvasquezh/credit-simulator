package com.thomas.creditsimulator.customer.api;

import com.thomas.creditsimulator.customer.api.request.CreateCustomerRequest;
import com.thomas.creditsimulator.customer.api.request.UpdateCustomerRequest;
import com.thomas.creditsimulator.customer.api.response.CustomerResponse;
import com.thomas.creditsimulator.customer.application.CustomerService;
import com.thomas.creditsimulator.customer.domain.Customer;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@Valid @RequestBody CreateCustomerRequest request) {
        Customer customer = customerMapper.toDomain(request);
        Customer savedCustomer = customerService.create(customer);
        CustomerResponse response = customerMapper.toResponse(savedCustomer);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<Customer> customers = customerService.findAll();
        List<CustomerResponse> response = customerMapper.toResponseList(customers);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerByid(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        CustomerResponse response = customerMapper.toResponse(customer);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCustomerRequest request) {

        Customer updateCostumer = customerService.update(id, request);
        CustomerResponse response = customerMapper.toResponse(updateCostumer);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
