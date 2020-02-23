package com.app.credit.DAO;

import com.app.credit.Entity.CreditLimit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreditLimitRepository extends MongoRepository<CreditLimit, Integer> {
}
