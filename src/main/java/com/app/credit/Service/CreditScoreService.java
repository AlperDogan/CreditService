package com.app.credit.Service;

import com.app.credit.DAO.CreditScoreDAO;
import com.app.credit.Entity.CreditScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditScoreService {

    @Autowired
    private CreditScoreDAO creditScoreDAO;

    public Optional<CreditScore> getCreditScoreByIdentiyNumber(int identityNumber) {
        return creditScoreDAO.getCreditScoreByIdentityNumber(identityNumber);
    }
}
