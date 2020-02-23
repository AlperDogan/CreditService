package com.app.credit.DAO;

import com.app.credit.Entity.CreditScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreditScoreDAO {
    @Autowired
    private CreditScoreRepository creditScoreRepository;

    public Optional<CreditScore> getCreditScoreByIdentityNumber(Integer identityNumber) {
        return creditScoreRepository.findById(identityNumber);
    }

}
