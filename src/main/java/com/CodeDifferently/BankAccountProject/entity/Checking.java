package com.CodeDifferently.BankAccountProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Checking {

    @Id
    @GeneratedValue
    private Long id;

    // Instance variables
    private String description;
    private double transactionAmount;
    private Date date;

    // Establish a many-to-one relationship with the customer class
    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    private Customer customer;

    // Constructors
    public Checking(String description, double transactionAmount, Date date) {
        this.description = description;
        this.transactionAmount = transactionAmount;
        this.date = date;
    }

    public Checking(){}

    @Override
    public String toString() {
        return "Checking{" +
                "description='" + description + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", date=" + date +
                '}';
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
