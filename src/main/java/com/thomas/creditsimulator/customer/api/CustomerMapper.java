package com.thomas.creditsimulator.customer.api;

import com.thomas.creditsimulator.customer.api.request.CreateCustomerRequest;
import com.thomas.creditsimulator.customer.api.response.CustomerResponse;
import com.thomas.creditsimulator.customer.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {
    public Customer toDomain(CreateCustomerRequest request) {
        return Customer.builder()
                .documentType(request.documentType())
                .documentNumber(request.documentNumber())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phone(request.phone())
                .monthlyIncome(request.monthlyIncome())
                .build();
    }

    public CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getDocumentType(),
                customer.getDocumentNumber(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getMonthlyIncome(),
                customer.getStatus(),
                customer.getCreatedAt()
        );
    }

    public List<CustomerResponse> toResponseList(List<Customer> customers) {
        return customers.stream()
                .map(this::toResponse)
                .toList();
    }


}
