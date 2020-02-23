package com.app.credit.DAO;

import com.app.credit.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class CustomerDAO {
    @Autowired
    private CustomerRepository repository;

    public Collection<Customer> getCustomers() {
        return repository.findAll();
    }

    public Customer createCustomer(Customer customer) {
        return repository.insert(customer);
    }

    public Optional<Customer> getCustomerByIdentityNumber(int identityNumber) {
        return repository.findById(identityNumber);
    }

    public Customer updateCustomer(Customer customer) {
        return repository.save(customer);
    }

    public Optional<Customer> deleteCustomerByIdentityNumber(int identityNumber) {
        Optional<Customer> customer = repository.findById(identityNumber);
        customer.ifPresent(c -> repository.delete(c));
        return customer;
    }
}


