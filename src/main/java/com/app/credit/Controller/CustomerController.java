package com.app.credit.Controller;

import com.app.credit.Entity.Customer;
import com.app.credit.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Collection<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @GetMapping(value = "/{identityNumber}")
    public Optional<Customer> getCustomerByIdentityNumber(@PathVariable("identityNumber") int identityNumber) {
        return customerService.getCustomerByIdentiyNumber(identityNumber);
    }

    public Optional<Customer> deleteCustomerByIdentityNumber(int identityNumber) {
        return customerService.deleteCustomerByIdentiyNumber(identityNumber);
    }
}
