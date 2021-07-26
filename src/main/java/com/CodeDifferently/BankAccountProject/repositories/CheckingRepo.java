package com.CodeDifferently.BankAccountProject.repositories;

import com.CodeDifferently.BankAccountProject.entity.Checking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckingRepo extends JpaRepository<Checking, Long> {
}
