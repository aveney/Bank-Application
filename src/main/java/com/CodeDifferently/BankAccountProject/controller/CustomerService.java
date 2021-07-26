package com.CodeDifferently.BankAccountProject.controller;

import com.CodeDifferently.BankAccountProject.entity.Checking;
import com.CodeDifferently.BankAccountProject.entity.Customer;
import com.CodeDifferently.BankAccountProject.entity.Savings;
import com.CodeDifferently.BankAccountProject.exceptions.InvalidCustomerException;
import com.CodeDifferently.BankAccountProject.repositories.CheckingRepo;
import com.CodeDifferently.BankAccountProject.repositories.CustomerRepo;
import com.CodeDifferently.BankAccountProject.repositories.SavingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class CustomerService {

    @Autowired
    public CustomerRepo customerRepo;

    @Autowired
    public CheckingRepo checkingRepo;

    @Autowired
    public SavingsRepo savingsRepo;

    // Retrieve all customers
    @GetMapping("/bank")
    public List<Customer> returnAllCustomers() {
        return customerRepo.findAll();
    }

    // Return a single customer
    @GetMapping("/bank/{id}")
    public Customer getCustomer(@PathVariable Long id) {

        Optional<Customer> myCustomer = customerRepo.findById(id);

        if (!myCustomer.isPresent()) {
            throw new InvalidCustomerException("Cannot find customer with id-" + id);
        }
        return myCustomer.get();
    }

    // Remove a single customer
    @DeleteMapping("bank/{id}")
    public void removeCustomer(@PathVariable Long id) {
        Optional<Customer> myCustomer = customerRepo.findById(id);

        if (!myCustomer.isPresent()) {
            throw new InvalidCustomerException("Cannot find customer with id-" + id);
        }
        customerRepo.deleteById(id);
    }

    // Create a new customer
    @PostMapping("/bank")
    public void createNewCustomer(@RequestBody Customer customer) {
        customerRepo.save(customer);
    }

    // Update a customer
    @PutMapping("bank/{id}")
    public ResponseEntity updateClient(@PathVariable Long id, @RequestBody Customer customer) {
        Customer currentCustomer = customerRepo.findById(id).orElseThrow(RuntimeException::new);
        currentCustomer.setName(customer.getName());
        currentCustomer.setCheckingAmount(customer.getCheckingAmount());
        currentCustomer.setSavingAmount(customer.getSavingAmount());
        currentCustomer = customerRepo.save(customer);

        return ResponseEntity.ok(currentCustomer);
    }

    // Create a new checking transaction
    @PostMapping("/bank/{id}/checking")
    public Checking newCheckingTransaction(@RequestBody Checking checking, @PathVariable Long id) {
        Optional<Customer> myCustomer = customerRepo.findById(id);

        if (!myCustomer.isPresent()) {
            throw new InvalidCustomerException("Cannot find customer with id-" + id);
        }

        Customer thisCustomer = myCustomer.get();

        checking.setCustomer(thisCustomer);
        thisCustomer.setCheckingAmount(thisCustomer.getCheckingAmount() + checking.getTransactionAmount());
        return checkingRepo.save(checking);
    }

    // Get all checking transactions for a user
    @GetMapping("/bank/{id}/checking")
    public List<Checking> getCheckingTransactions(@PathVariable Long id) {
        Optional<Customer> myCustomer = customerRepo.findById(id);

        if (!myCustomer.isPresent()) {
            throw new InvalidCustomerException("Cannot find customer with id-" + id);
        }

        return myCustomer.get().getCheckingList();
    }

    // Create a new savings transaction
    @PostMapping("/bank/{id}/savings")
    public Savings newSavingsTransaction(@RequestBody Savings savings, @PathVariable Long id) {
        Optional<Customer> myCustomer = customerRepo.findById(id);

        if (!myCustomer.isPresent()) {
            throw new InvalidCustomerException("Cannot find customer with id-" + id);
        }

        Customer thisCustomer = myCustomer.get();

        savings.setCustomer(thisCustomer);
        thisCustomer.setSavingAmount(thisCustomer.getCheckingAmount() + savings.getTransactionAmount());
        return savingsRepo.save(savings);
    }

    // Get all savings transactions for a user
    @GetMapping("/bank/{id}/savings")
    public List<Savings> getSavingsTransactions(@PathVariable Long id) {
        Optional<Customer> myCustomer = customerRepo.findById(id);

        if (!myCustomer.isPresent()) {
            throw new InvalidCustomerException("Cannot find customer with id-" + id);
        }

        return myCustomer.get().getSavingsList();
    }

    // checking to saving
    @PostMapping("/bank/checkingToSaving/{id}")
    Checking checkingToSaving(@RequestBody Checking customer, @PathVariable Long id){
        Optional<Customer> user = customerRepo.findById(id);

        if (!user.isPresent()) {
            throw new InvalidCustomerException("Cannot find customer with id-" +id);
        }

        // Get the customer from the id
        Customer thisCustomer = user.get();

        // Create a new savings transaction
        Savings customer2 = new Savings();

        customer.setCustomer(thisCustomer);
        customer2.setCustomer(thisCustomer);

        thisCustomer.setCheckingAmount(thisCustomer.getCheckingAmount()-customer.getTransactionAmount());
        thisCustomer.setSavingAmount(thisCustomer.getSavingAmount()+customer.getTransactionAmount());

        customer2.setTransactionAmount(customer.getTransactionAmount());
        customer2.setDescription("transfer from checking");
        customer.setDescription("transfer from checking");

        savingsRepo.save(customer2);
        return checkingRepo.save(customer);
    }

    //saving to checking
    @PostMapping("/bank/savingToChecking/{id}")
    Savings savingToChecking(@RequestBody Savings customer,@PathVariable Long id){
        Optional<Customer> user = customerRepo.findById(id);

        if (!user.isPresent()) {
            throw new InvalidCustomerException("Cannot find customer with id-" +id);
        }

        Checking customer2 = new Checking();
        Customer thisCustomer = user.get();

        customer.setCustomer(thisCustomer);
        customer2.setCustomer(thisCustomer);

        thisCustomer.setCheckingAmount(customer.getTransactionAmount());
        thisCustomer.setSavingAmount(-customer.getTransactionAmount());

        customer2.setTransactionAmount(customer.getTransactionAmount());
        customer2.setDescription("transfer from saving");

        checkingRepo.save(customer2);
        return savingsRepo.save(customer);
    }
}
