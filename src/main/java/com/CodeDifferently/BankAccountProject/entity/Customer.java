package com.CodeDifferently.BankAccountProject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    // Instance variables
    private String name;
    private double checkingAmount;
    private double savingAmount;
    private Date startDate;

    // Establish a one-to-many relationship with checking transactions
    @OneToMany(mappedBy = "customer")
    private List<Checking> checkingList;

    @OneToMany(mappedBy = "customer")
    private List<Savings> savingsList;


    // Constructors
    private Customer(){}

    public Customer(String name, double checkingAmount, double savingAmount) {
        this.name = name;
        this.checkingAmount = checkingAmount;
        this.savingAmount = savingAmount;
        this.startDate = new Date();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", checkingAmount=" + checkingAmount +
                ", savingAmount=" + savingAmount +
                ", startDate=" + startDate +
                '}';
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCheckingAmount() {
        return checkingAmount;
    }

    public void setCheckingAmount(double checkingAmount) {
        this.checkingAmount += checkingAmount;
    }

    public double getSavingAmount() {
        return savingAmount;
    }

    public void setSavingAmount(double savingAmount) {
        this.savingAmount += savingAmount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<Checking> getCheckingList() {
        return checkingList;
    }

    public void setCheckingList(List<Checking> checkingList) {
        this.checkingList = checkingList;
    }

    public List<Savings> getSavingsList() {
        return savingsList;
    }

    public void setSavingsList(List<Savings> savingsList) {
        this.savingsList = savingsList;
    }
}
