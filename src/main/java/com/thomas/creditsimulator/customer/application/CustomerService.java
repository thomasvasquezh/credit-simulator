package com.thomas.creditsimulator.customer.application;

import com.thomas.creditsimulator.common.NotFoundException;
import com.thomas.creditsimulator.customer.api.request.UpdateCustomerRequest;
import com.thomas.creditsimulator.customer.domain.Customer;
import com.thomas.creditsimulator.customer.domain.CustomerStatus;
import com.thomas.creditsimulator.customer.infrastructure.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer create(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> findAll(){
        return customerRepository.findByStatus(CustomerStatus.ACTIVE);
    }

    public Customer findById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found with ID : " + id));
    }

    public Customer update(Long id, UpdateCustomerRequest request){
        Customer customer = customerRepository.findById(id).
                orElseThrow(()->new NotFoundException("Customer not found with ID " + id));
            customer.setFirstName(request.firstName());
            customer.setLastName(request.lastName());
            customer.setEmail(request.email());
            customer.setPhone(request.phone());
            customer.setMonthlyIncome(request.monthlyIncome());

            return customerRepository.save(customer);
    }

    public void delete(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Customer not found with ID: " + id));
        customer.setStatus(CustomerStatus.INACTIVE);
        customerRepository.save(customer);

    }



}
