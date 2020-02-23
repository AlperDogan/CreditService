package com.app.credit.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "CreditLimit")
public class CreditLimit {

    private Integer identityNumber;
    private Integer limit;
    private Date transactionDate;

    public CreditLimit(Integer identityNumber, Integer limit, Date transactionDate) {
        this.identityNumber = identityNumber;
        this.limit = limit;
        this.transactionDate = transactionDate;
    }

    public Integer getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(Integer identityNumber) {
        this.identityNumber = identityNumber;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
