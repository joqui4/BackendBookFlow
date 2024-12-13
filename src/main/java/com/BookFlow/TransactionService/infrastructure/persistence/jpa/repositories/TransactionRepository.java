package com.BookFlow.TransactionService.infrastructure.persistence.jpa.repositories;

import com.BookFlow.TransactionService.domain.model.aggregates.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
