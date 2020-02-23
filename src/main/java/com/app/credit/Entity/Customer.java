package com.app.credit.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Customer")
public class Customer {

    @Id
    private Integer identityNumber;
    private String name;
    private String surname;
    private Integer mountlySalary;
    private String phoneNumber;

    public Customer(Integer identityNumber, String name, String surname, Integer mountlySalary, String phoneNumber) {
        this.identityNumber = identityNumber;
        this.name = name;
        this.surname = surname;
        this.mountlySalary = mountlySalary;
        this.phoneNumber = phoneNumber;
    }

    public Integer getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(Integer identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getMountlySalary() {
        return mountlySalary;
    }

    public void setMountlySalary(Integer mountlySalary) {
        this.mountlySalary = mountlySalary;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
