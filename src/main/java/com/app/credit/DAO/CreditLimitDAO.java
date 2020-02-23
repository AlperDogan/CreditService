package com.app.credit.DAO;

import com.app.credit.Entity.CreditLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditLimitDAO {

    @Autowired
    private CreditLimitRepository creditLimitRepository;


    public CreditLimit create(CreditLimit creditLimit) {
        return creditLimitRepository.insert(creditLimit);
    }

}
