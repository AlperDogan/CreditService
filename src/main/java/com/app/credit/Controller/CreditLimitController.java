package com.app.credit.Controller;

import com.app.credit.Entity.CreditLimit;
import com.app.credit.Entity.Customer;
import com.app.credit.Exception.CreditScoreNotFoundException;
import com.app.credit.Exception.CustomerNotFoundException;
import com.app.credit.Service.CreditLimitService;
import com.app.credit.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credits")
public class CreditLimitController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CreditLimitService creditLimitService;

    @PostMapping
    public CreditLimit apply(@RequestBody Customer customer) throws CreditScoreNotFoundException, CustomerNotFoundException {
        if (!customerService.getCustomerByIdentiyNumber(customer.getIdentityNumber()).isPresent()) {
            customerService.createCustomer(customer);
        } else {
            // Customer's latest salary income may have changed, update it
            customerService.updateCustomer(customer);
        }
        return creditLimitService.apply(customer.getIdentityNumber());
    }


}
