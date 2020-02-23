package com.app.credit.Service;

import com.app.credit.DAO.CreditLimitDAO;
import com.app.credit.Entity.CreditLimit;
import com.app.credit.Entity.CreditScore;
import com.app.credit.Entity.Customer;
import com.app.credit.Exception.CreditScoreNotFoundException;
import com.app.credit.Exception.CustomerNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class CreditLimitServiceTest {

    @InjectMocks
    private CreditLimitService mockCreditLimitService;

    @Mock
    private CreditScoreService mockCreditScoreService;

    @Mock
    private CreditLimitDAO mockCreditLimitDAO;

    @Mock
    private CustomerService mockCustomerService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        // We dont need any persistent data in mongodb anyway, ignore
        when(mockCreditLimitDAO.create(Mockito.any())).thenReturn(null);
    }

    @Test
    public void applyRejectTest() throws CustomerNotFoundException, CreditScoreNotFoundException {
        when(mockCreditScoreService.getCreditScoreByIdentiyNumber(1))
                .thenReturn(Optional.of(new CreditScore(1, 499)));
        CreditLimit apply = mockCreditLimitService.apply(1);
        assertEquals(apply.getLimit().intValue(), 0);
    }

    @Test
    public void applyAcceptWith10000Test() throws CustomerNotFoundException, CreditScoreNotFoundException {
        when(mockCreditScoreService.getCreditScoreByIdentiyNumber(1))
                .thenReturn(Optional.of(new CreditScore(1, 500)));
        when(mockCustomerService.getCustomerByIdentiyNumber(1))
                .thenReturn(Optional.of(new Customer(1,
                        "alper",
                        "dogan",
                        4_999,
                        "123")));
        CreditLimit apply = mockCreditLimitService.apply(1);
        assertEquals(apply.getLimit().intValue(), 10_000);
    }

    @Test
    public void applyAcceptWith20000Test() throws CustomerNotFoundException, CreditScoreNotFoundException {
        when(mockCreditScoreService.getCreditScoreByIdentiyNumber(1))
                .thenReturn(Optional.of(new CreditScore(1, 500)));
        when(mockCustomerService.getCustomerByIdentiyNumber(1))
                .thenReturn(Optional.of(new Customer(1, "alper", "dogan", 5_001, "123")));
        CreditLimit apply = mockCreditLimitService.apply(1);
        assertEquals(apply.getLimit().intValue(), 20_000);
    }

    @Test
    public void applyAcceptWithWithSalaryXFourTest() throws CustomerNotFoundException, CreditScoreNotFoundException {
        when(mockCreditScoreService.getCreditScoreByIdentiyNumber(1))
                .thenReturn(Optional.of(new CreditScore(1, 1001)));
        when(mockCustomerService.getCustomerByIdentiyNumber(1))
                .thenReturn(Optional.of(new Customer(1, "alper", "dogan", 5_000, "123")));
        CreditLimit apply = mockCreditLimitService.apply(1);
        assertEquals(apply.getLimit().intValue(), 5_000 * CreditLimitService.CREDIT_FACTOR);
    }

}
