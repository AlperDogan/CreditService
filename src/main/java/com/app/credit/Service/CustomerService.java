package com.app.credit.Service;

import com.app.credit.DAO.CustomerDAO;
import com.app.credit.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    public Collection<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }

    public Customer createCustomer(Customer customer) {
        return customerDAO.createCustomer(customer);
    }

    public Optional<Customer> getCustomerByIdentiyNumber(int identityNumber) {
        return customerDAO.getCustomerByIdentityNumber(identityNumber);
    }

    public Customer updateCustomer(Customer customer) {
        return customerDAO.updateCustomer(customer);
    }

    public Optional<Customer> deleteCustomerByIdentiyNumber(int identityNumber) {
        return customerDAO.deleteCustomerByIdentityNumber(identityNumber);
    }

}

