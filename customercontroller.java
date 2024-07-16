package com.example.demo.controller;

import com.example.demo.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>();

    // Endpoint to add a new Customer
    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        customer.setId((long) (customers.size() + 1)); // Example: auto-incrementing ID
        customers.add(customer);
        return customer;
    }

    // Endpoint to find a Customer by ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        Optional<Customer> optionalCustomer = customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
        return optionalCustomer.orElse(null);
    }

    // Endpoint to get all Customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customers;
    }

    // Endpoint to update a Customer by ID
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Optional<Customer> optionalCustomer = customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            return existingCustomer;
        }
        return null;
    }
}

