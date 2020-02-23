package com.app.credit.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CreditScore")
public class CreditScore {

    @Id
    private Integer identityNumber;
    private Integer creditScore;

    public CreditScore(Integer identityNumber, Integer creditScore) {
        this.identityNumber = identityNumber;
        this.creditScore = creditScore;
    }

    public Integer getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(Integer identityNumber) {
        this.identityNumber = identityNumber;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }
}
