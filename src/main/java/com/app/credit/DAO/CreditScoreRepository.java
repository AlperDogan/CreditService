package com.app.credit.DAO;

import com.app.credit.Entity.CreditScore;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreditScoreRepository extends MongoRepository<CreditScore, Integer> {
}
