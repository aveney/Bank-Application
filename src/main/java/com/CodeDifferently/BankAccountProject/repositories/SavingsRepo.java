package com.CodeDifferently.BankAccountProject.repositories;

import com.CodeDifferently.BankAccountProject.entity.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsRepo extends JpaRepository<Savings, Long> {
}
