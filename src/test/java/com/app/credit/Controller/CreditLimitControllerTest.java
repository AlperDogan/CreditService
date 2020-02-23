package com.app.credit.Controller;

import com.app.credit.Entity.Customer;
import com.app.credit.Exception.CreditScoreNotFoundException;
import com.app.credit.Exception.CustomerNotFoundException;
import com.app.credit.Service.CreditLimitService;
import com.app.credit.Service.CustomerService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class CreditLimitControllerTest {

    @InjectMocks
    private CreditLimitController mockCreditLimitController;
    @Mock
    private CustomerService mockCustomerService;
    @Mock
    private CreditLimitService mockCreditLimitService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void applyWithNewCustomerTest() throws CreditScoreNotFoundException, CustomerNotFoundException {
        when(mockCustomerService.getCustomerByIdentiyNumber(9999)).thenReturn(Optional.empty());
        Customer customerNonExistence = new Customer(9999,
                "new",
                "customer",
                1_000,
                "555");

        List<Customer> mockCustomerDb = new ArrayList<>();
        doAnswer(invocation -> {
            Customer toSave = invocation.getArgument(0, Customer.class);
            mockCustomerDb.add(toSave);
            return toSave;
        }).when(mockCustomerService).createCustomer(customerNonExistence);

        mockCreditLimitController.apply(customerNonExistence);
        Assert.assertEquals(customerNonExistence.getIdentityNumber(), mockCustomerDb.get(0).getIdentityNumber());
    }

    @Test
    public void applyWithUpdatedCustomerTest() throws CreditScoreNotFoundException, CustomerNotFoundException {
        Customer customerUpdated = new Customer(100,
                "new",
                "customer",
                1_000,
                "555");
        when(mockCustomerService.getCustomerByIdentiyNumber(100)).thenReturn(Optional.of(customerUpdated));
        List<Customer> mockCustomerDb = new ArrayList<>();
        mockCustomerDb.add(customerUpdated);
        //Simulate customer changed his phone number
        customerUpdated = new Customer(100,
                "new",
                "customer",
                1_000,
                "444");

        doAnswer(invocation -> {
            Customer toUpdate = invocation.getArgument(0, Customer.class);
            mockCustomerDb.remove(0);
            mockCustomerDb.add(toUpdate);
            return toUpdate;
        }).when(mockCustomerService).updateCustomer(customerUpdated);

        Assert.assertEquals(mockCustomerDb.get(0).getPhoneNumber(),"555");
        mockCreditLimitController.apply(customerUpdated);
        Assert.assertEquals(mockCustomerDb.get(0).getPhoneNumber(),"444");
    }

}
