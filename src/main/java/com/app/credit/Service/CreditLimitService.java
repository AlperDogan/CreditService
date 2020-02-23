package com.app.credit.Service;

import com.app.credit.DAO.CreditLimitDAO;
import com.app.credit.Entity.CreditLimit;
import com.app.credit.Entity.CreditScore;
import com.app.credit.Entity.Customer;
import com.app.credit.Exception.CreditScoreNotFoundException;
import com.app.credit.Exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreditLimitService {
    public static final int CREDIT_LOWER_THRESHOLD = 500;
    public static final int CREDIT_UPPER_THRESHOLD = 1_000;
    public static final int SALARY_LOWER_THRESHOLD = 5_000;
    public static final int CREDIT_FACTOR = 4;

    @Autowired
    private CreditScoreService creditScoreService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CreditLimitDAO creditLimitDAO;

    public CreditLimit apply(int identityNumber) throws CustomerNotFoundException, CreditScoreNotFoundException {
        CreditScore creditScore = creditScoreService.getCreditScoreByIdentiyNumber(identityNumber).
                orElseThrow(CreditScoreNotFoundException::new);
        Integer score = creditScore.getCreditScore();
        int calculatedLimit;
        if (score < CREDIT_LOWER_THRESHOLD) {
            calculatedLimit = 0;
        } else {
            Customer cust = customerService.getCustomerByIdentiyNumber(identityNumber)
                    .orElseThrow(CustomerNotFoundException::new);
            Integer mountlySalary = cust.getMountlySalary();

            if (score < CREDIT_UPPER_THRESHOLD) {
                if (mountlySalary < SALARY_LOWER_THRESHOLD) {
                    calculatedLimit = 10_000;
                } else {
                    // TODO Unspecified in question
                    calculatedLimit = 20_000;
                }
            } else {
                calculatedLimit = CREDIT_FACTOR * mountlySalary;
            }
        }

        CreditLimit limit = new CreditLimit(identityNumber, calculatedLimit, new Date());
        creditLimitDAO.create(limit);
        return limit;
    }

}
