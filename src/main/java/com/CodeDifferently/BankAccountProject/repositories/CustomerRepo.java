package com.CodeDifferently.BankAccountProject.repositories;

import com.CodeDifferently.BankAccountProject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
